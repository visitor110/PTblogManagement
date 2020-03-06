package com.pt.bloglib.dao.pojo;

import lombok.Data;

@Data
public class LoginUserPojo {
    private String username;
    private String password;
    private Boolean remember;
}
