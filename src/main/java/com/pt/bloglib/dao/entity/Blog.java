package com.pt.bloglib.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Blog {
    private Integer id;
    private String title;
    private String content;
    private Integer watchedNum;
    private Integer commentNum;
    private LocalDateTime createDate;
    private Integer state;
    private Integer userId;
}
