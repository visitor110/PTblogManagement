package com.pt.bloglib.dao.pojo;

import lombok.Data;

/**
 * 接收前端传来的博客信息和用户信息
 */
@Data
public class ReceiveBlog {

    private String blog;
    private Integer userId;
    private String username;
    private String title;
}
