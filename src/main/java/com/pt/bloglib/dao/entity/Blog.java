package com.pt.bloglib.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private Integer id;
    private String title;
    private String content;
    private Integer watchedNum;
    private Integer commentNum;
    private Date createDate;
    private Integer state;
    private Integer userId;
}
