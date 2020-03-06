package com.pt.bloglib.dao.pojo;

import com.pt.bloglib.dao.entity.Discuss;
import lombok.Data;

/**
 * 返回评论和对应用户
 */
@Data
public class LoadDiscussPojo {
    private Integer id;
    private Discuss discuss;
    private String username;
    private String avatar;
}
