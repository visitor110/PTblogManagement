package com.pt.bloglib.dao.pojo;

import com.pt.bloglib.dao.entity.Discuss;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回评论和对应用户
 */
@Data
public class LoadDiscussPojo implements Serializable {
    private Integer id;
    private Discuss discuss;
    private List<ReplyPojo> replyPojoList;
    private String username;
    private String avatar;
}
