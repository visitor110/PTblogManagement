package com.pt.bloglib.dao.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.pt.bloglib.dao.entity")
public class javaBeans {

    @Bean
    public User getUser() {
        return new User();
    }

    @Bean
    public Blog getBlog() {
        return new Blog();
    }
}
