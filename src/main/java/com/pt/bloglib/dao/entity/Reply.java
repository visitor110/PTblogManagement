package com.pt.bloglib.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {

    private Integer replyId;
    private String content;
    private LocalDateTime createDate;
    private Integer discussId;
    private Integer userId;
    private Integer targetUserId;
    private Integer rootId;

}
