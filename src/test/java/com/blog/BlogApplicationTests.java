package com.blog;

import com.blog.mapper.TBlogMapper;
import com.blog.mapper.TCategoryMapper;
import com.blog.mapper.TCommentMapper;
import com.blog.mapper.TUserMapper;
import com.blog.pojo.TBlog;
import com.blog.pojo.TCategory;
import com.blog.pojo.TComment;
import com.blog.pojo.TUser;
import com.blog.service.TCommentService;
import com.blog.service.TUserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    TBlogMapper tBlogMapper;

    @Autowired
    TCategoryMapper categoryMapper;

    @Autowired
    TCommentService commentService;
    @Autowired
    TUserService userService;
    @Autowired
    TUserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(tBlogMapper.sixBlog());
    }

}
