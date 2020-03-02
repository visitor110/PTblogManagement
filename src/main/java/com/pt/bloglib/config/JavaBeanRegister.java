package com.pt.bloglib.config;

import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.pt.bloglib.dao.entity", "com.pt.bloglib.dao.pojo"})
public class JavaBeanRegister {

    @Bean
    public User getUser() {
        return new User();
    }

    @Bean
    public Blog getBlog() {
        return new Blog();
    }

    @Bean
    public UserInfo getUserInfo() {
        return new UserInfo();
    }
}
