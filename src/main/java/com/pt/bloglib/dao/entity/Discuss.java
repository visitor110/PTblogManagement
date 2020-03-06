package com.pt.bloglib.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Discuss {
    private Integer id;
    private String content;
    private LocalDateTime createDate;
    private Integer userId;
    private Integer blogId;
}
