package com.pt.bloglib.dao.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String mail;
    private Integer state;
    private String avatar;
}
