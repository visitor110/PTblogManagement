package com.pt.bloglib.service;

import com.pt.bloglib.Exception.DiscussSaveException;
import com.pt.bloglib.dao.entity.Discuss;
import com.pt.bloglib.dao.pojo.LoadDiscussPojo;

import java.util.List;

public interface DiscussService {
    void saveDiscuss(Discuss discuss) throws DiscussSaveException;

    List<LoadDiscussPojo> loadDiscussByBlogId(String blogId);
}
