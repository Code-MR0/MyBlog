package com.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description Druid Config
 * @Author MR
 * @Date 2021/4/15 14:39
 */
@Configuration
public class MysqlDruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean StatViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台账号密码登录
        HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "root");//账户
        initParameters.put("loginPassword", "root");//密码
        //允许谁可以访问 ip
        initParameters.put("allow", "");//参数为空 所有都可以
        //禁止谁可以访问 ip
        //initParameters.put("thunder","");
        bean.setInitParameters(initParameters);//初始化参数
        return bean;
    }

//    //过滤器
//    public FilterRegistrationBean webStartFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new WebStatFilter());
//
//        //过滤的请求
//        Map<String, String> initParameters = new HashMap<>();
//        initParameters.put("exclusions", "*.js,*.css,/druid/*");
//        bean.setInitParameters(initParameters);
//        return bean;
//
//    }
}
