package com.pt.bloglib.service;

import com.pt.bloglib.dao.entity.Tag;

import java.util.List;

public interface TagService {

    default Integer saveTags(List<String> tagList, Integer blogId){return null;}

    default List<Tag> selectTagsByBlogId(Integer blogId){return null;}
}
