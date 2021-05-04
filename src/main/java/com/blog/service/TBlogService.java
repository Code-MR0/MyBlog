package com.blog.service;

import com.blog.pojo.TBlog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MR
 * @since 2021-04-16
 */
public interface TBlogService extends IService<TBlog> {

    int addBlog(TBlog blog);

    int updateBlog(TBlog blog);

    int deleteBlog(Long id);

    TBlog findBlogById(Long id);

    HashMap<String, Object> blogList(Long pn, Long size, String category, String title);

    List<TBlog> allBlog();

    List<TBlog> sixBlog();

    List<TBlog> findByText(String text);

    int blogCount();

    int addView(Long id);

}
