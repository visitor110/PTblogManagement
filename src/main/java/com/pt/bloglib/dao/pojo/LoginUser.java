package com.pt.bloglib.dao.pojo;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    private Boolean remember;
}
