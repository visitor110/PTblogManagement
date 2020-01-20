package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.BlogIsNullException;
import com.pt.bloglib.dao.BlogDao;
import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dao.pojo.ReceiveBlog;
import com.pt.bloglib.service.BlogService;
import com.pt.bloglib.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogSeviceImpl implements BlogService {

    private Blog blog;
    private BlogDao blogDao;

    @Override
    public void saveBlog(ReceiveBlog receiveBlog) throws BlogIsNullException {
        if (FormatUtil.checkStringIsNull(receiveBlog.getTitle(), receiveBlog.getBlog()))
            throw new BlogIsNullException("标题或内容不能为空");
        blog.setUserId(receiveBlog.getUserId());
        blog.setTitle(receiveBlog.getTitle());
        blog.setContent(receiveBlog.getBlog());
        blog.setCommentNum(0);
        blog.setWatchedNum(0);
        blog.setState(1);
        int result = blogDao.saveBlog(blog);
        System.out.println(result);
    }

    @Autowired
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Autowired
    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }
}
