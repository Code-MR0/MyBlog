package com.blog.mapper;

import com.blog.pojo.TFriends;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MR
 * @since 2021-04-20
 */
@Repository
@Mapper
public interface TFriendsMapper extends BaseMapper<TFriends> {

}
