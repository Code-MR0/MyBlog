package com.blog.utils;

import com.blog.pojo.TUser;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //转换成request对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取session对象
        HttpSession session = request.getSession();
        //获取登录用户
        TUser adminUser = (TUser) session.getAttribute("adminUser");
        //获取当前的url
        String url = request.getRequestURI() + "";

        //用户是否访问的是index页面
        if (url.equals("/admin/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (adminUser != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (url.contains("/admin/")) {
            request.getRequestDispatcher("/admin/login").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
