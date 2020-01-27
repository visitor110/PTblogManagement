package com.pt.bloglib.service;

import com.pt.bloglib.dao.entity.Tag;

import java.util.List;

public interface TagService {

    default Integer saveTags(List<Tag> tagList){return null;}
}
