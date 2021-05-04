package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @version 1.0
 * @Description Swagger2 Config
 * @Author MR
 * @Date 2021/4/15 14:39
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final Profiles profiles = Profiles.of("dev");

    @Bean
    public Docket docketAll(Environment environment) {
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(myApiInfo())
                .enable(flag)
                .groupName("all");
    }

    @Bean
    public Docket docket(Environment environment) {
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                // 配置项目和个人信息
                .apiInfo(myApiInfo())
                // 配置扫描
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blog.controller"))
                // 配置过滤
                //.paths(PathSelectors.ant("/**"))
                .build()
                // 是否启用
                .enable(flag)
                .groupName("mhw");
    }

    public ApiInfo myApiInfo() {
        Contact contact = new Contact("马洪伟", "https://www.mahongwei.top/", "1132493269@qq.com");
        return new ApiInfo("崇明鸟Swagger", "个人博客项目", "1.0", "https://www.mahongwei.top/",
                contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }
}
