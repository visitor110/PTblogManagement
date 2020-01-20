package com.pt.bloglib.service;

import com.pt.bloglib.Exception.BlogIsNullException;
import com.pt.bloglib.dao.pojo.ReceiveBlog;

public interface BlogService {
    default void saveBlog(ReceiveBlog receiveBlog ) throws BlogIsNullException {}
}
