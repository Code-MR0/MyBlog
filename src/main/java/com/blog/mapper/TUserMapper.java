package com.blog.mapper;

import com.blog.pojo.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MR
 * @since 2021-04-25
 */
@Repository
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {

    TUser getOne();
}
