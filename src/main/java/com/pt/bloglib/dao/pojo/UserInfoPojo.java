package com.pt.bloglib.dao.pojo;

import lombok.Data;

import java.util.List;

/**
 * 由于存放用户相关的信息
 */
@Data
public class UserInfoPojo {
    private Integer userId;
    private String userName;
    private String imgAddress;
    private List<String> roles;
}
