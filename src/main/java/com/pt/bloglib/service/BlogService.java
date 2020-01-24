package com.pt.bloglib.service;

import com.pt.bloglib.Exception.BlogIsNullException;
import com.pt.bloglib.Exception.BlogSaveException;
import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dao.pojo.ReceiveBlog;

import java.util.List;

public interface BlogService {
    default void saveBlog(ReceiveBlog receiveBlog) throws BlogIsNullException, BlogSaveException {
    }

    default List<Blog> loadBlogs() {
        return null;
    }

    default List<Blog> loadBlogsByPage(Integer pageIndex,Integer blogsPerPage ) {
        return null;
    }
}
