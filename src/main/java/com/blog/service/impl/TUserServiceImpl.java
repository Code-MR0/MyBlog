package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.pojo.TUser;
import com.blog.mapper.TUserMapper;
import com.blog.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.utils.MdFive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MR
 * @since 2021-04-25
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Autowired
    private TUserMapper userMapper;
    @Autowired
    private MdFive mdFive;
    @Override
    public int addUser(TUser user) {
        user.setPassword(mdFive.encrypt(user.getPassword(),user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public TUser findUserByName(TUser user) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",user.getName());
        queryWrapper.eq("password",mdFive.encrypt(user.getPassword(),user.getPassword()));
        return userMapper.selectOne(queryWrapper);
    }
}
