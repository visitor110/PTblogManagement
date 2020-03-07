package com.pt.bloglib.dao.pojo;

import com.pt.bloglib.dao.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ReplyPojo implements Serializable {
    private Integer replyId;
    private String content;
    private LocalDateTime createDate;
    private Integer discussId;
    private User replyUser;
    private User targetUser;
}
