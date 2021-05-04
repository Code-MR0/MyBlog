package com.blog.service;

import com.blog.pojo.TFriends;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MR
 * @since 2021-04-20
 */
public interface TFriendsService extends IService<TFriends> {

    /**
     * 添加友链
     *
     * @param friend
     * @return
     */
    int addFriend(TFriends friend);

    /**
     * 删除友链
     *
     * @param id
     * @return
     */
    int deleteFriend(Long id);

    /**
     * 更新友链
     *
     * @param friend
     * @return
     */
    int updateFriend(TFriends friend);

    /**
     * 友链列表
     *
     * @param pn
     * @param size
     * @param name
     * @return
     */
    HashMap<String, Object> friendsList(Long pn, Long size, String name,Boolean type);

    /**
     * 所有友链
     * @param type
     * @return
     */
    List<TFriends> allFriend(Boolean type);
}
