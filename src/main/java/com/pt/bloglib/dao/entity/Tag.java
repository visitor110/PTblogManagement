package com.pt.bloglib.dao.entity;

import lombok.Data;

@Data
public class Tag {

    private Integer blogId;
    private Integer id;
    private String tagName;
}
