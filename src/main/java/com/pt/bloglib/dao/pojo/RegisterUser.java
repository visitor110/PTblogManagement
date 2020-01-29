package com.pt.bloglib.dao.pojo;

import lombok.Data;

/**
 * 接收前端注册时传来的用户数据
 */
@Data
public class RegisterUser {

    private String username;
    private String password;
    private String mail;
    private String verifyCode;
}
