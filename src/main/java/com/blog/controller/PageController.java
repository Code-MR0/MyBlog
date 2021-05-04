package com.blog.controller;

import com.blog.mapper.TBlogMapper;
import com.blog.pojo.TBlog;
import com.blog.service.TCategoryService;
import com.blog.utils.MarkDownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version 1.0
 * @Description TODO
 * @Author MR
 * @Date 2021/4/16 21:14
 */
@Controller
public class PageController {
    @Autowired
    private TBlogMapper blogMapper;
    @Autowired
    private TCategoryService categoryService;




    @GetMapping("/about")
    public String about() {
        return "about";
    }






}
