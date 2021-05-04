package com.blog.mapper;

import com.blog.pojo.TBlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MR
 * @since 2021-04-16
 */
@Repository
@Mapper
public interface TBlogMapper extends BaseMapper<TBlog> {
    List<TBlog> sixBlog();
}
