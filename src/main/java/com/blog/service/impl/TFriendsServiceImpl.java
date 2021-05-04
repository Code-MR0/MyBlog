package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.TCategory;
import com.blog.pojo.TFriends;
import com.blog.mapper.TFriendsMapper;
import com.blog.service.TFriendsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MR
 * @since 2021-04-20
 */
@Service
public class TFriendsServiceImpl extends ServiceImpl<TFriendsMapper, TFriends> implements TFriendsService {

    @Autowired
    private TFriendsMapper friendsMapper;

    /**
     * 获取分页友链数据
     *
     * @param pn
     * @param size
     * @param queryWrapper
     * @return
     */
    private HashMap<String, Object> getFriendsList(Long pn, Long size, QueryWrapper<TFriends> queryWrapper) {
        HashMap<String, Object> map = new HashMap<>();
        if (pn < 1)
            pn = 1L;
        Page<TFriends> page = new Page<>(pn, size);
        Page<TFriends> selectPage = friendsMapper.selectPage(page, queryWrapper);
        long pages = selectPage.getPages();
        if (pn > pages) {
            page = new Page<>(pages, size);
            selectPage = friendsMapper.selectPage(page, queryWrapper);
        }
        map.put("friendsList", selectPage.getRecords());
        map.put("pages", selectPage.getPages());
        return map;
    }

    /**
     * 添加友链
     *
     * @param friend
     * @return
     */
    @Override
    public int addFriend(TFriends friend) {
        return friendsMapper.insert(friend);
    }

    /**
     * 删除友链
     *
     * @param id
     * @return
     */
    @Override
    public int deleteFriend(Long id) {
        return friendsMapper.deleteById(id);
    }

    /**
     * 更新友链
     *
     * @param friend
     * @return
     */
    @Override
    public int updateFriend(TFriends friend) {
        TFriends selectById = friendsMapper.selectById(friend.getId());
        selectById.setName(friend.getName());
        selectById.setDescription(friend.getDescription());
        selectById.setType(friend.getType());
        selectById.setUpdateDate(new Date());
        if (friend.getAddress() != null && !"".equals(friend.getAddress()))
            selectById.setAddress(friend.getAddress());
        if (friend.getIcon() != null && !"".equals(friend.getIcon()))
            selectById.setIcon(friend.getIcon());
        return friendsMapper.updateById(selectById);
    }

    /**
     * 友链列表
     *
     * @param pn
     * @param size
     * @param name
     * @return
     */
    @Override
    public HashMap<String, Object> friendsList(Long pn, Long size, String name,Boolean type) {
        QueryWrapper<TFriends> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_date");
        if (name != null && !name.equals("")) {
            queryWrapper.like("name", name);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        return getFriendsList(pn, size, queryWrapper);
    }

    /**
     * 所有友链
     *
     * @param type
     * @return
     */
    @Override
    public List<TFriends> allFriend(Boolean type) {
        QueryWrapper<TFriends> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        return friendsMapper.selectList(queryWrapper);
    }
}
