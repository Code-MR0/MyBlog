package com.blog.controller;


import com.blog.pojo.TUser;
import com.blog.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MR
 * @since 2021-04-25
 */
@Controller
public class TUserController {
    @Autowired
    private TUserService userService;
    @GetMapping("/admin/login")
    public String about() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(TUser user, HttpServletRequest request){
        TUser userByName = userService.findUserByName(user);
        if (userByName!=null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("adminUser",userByName);
            return "redirect:/admin/blogList";
        }
        return "redirect:/admin/login";

    }
}

