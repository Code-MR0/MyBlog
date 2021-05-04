package com.blog.service;

import com.blog.pojo.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MR
 * @since 2021-04-25
 */
public interface TUserService extends IService<TUser> {
    int addUser(TUser user);

    TUser findUserByName(TUser user);
}
