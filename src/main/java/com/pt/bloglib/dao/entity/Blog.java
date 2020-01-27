package com.pt.bloglib.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Tag> tagList;
}
