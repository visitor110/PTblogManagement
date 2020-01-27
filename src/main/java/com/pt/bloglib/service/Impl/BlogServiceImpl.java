package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.BlogIsNullException;
import com.pt.bloglib.Exception.BlogSaveException;
import com.pt.bloglib.Exception.LoadBlogsException;
import com.pt.bloglib.dao.BlogDao;
import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dao.pojo.ReceiveBlog;
import com.pt.bloglib.service.BlogService;
import com.pt.bloglib.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private Blog blog;
    private BlogDao blogDao;

    @Override
    public void saveBlog(ReceiveBlog receiveBlog) throws BlogIsNullException, BlogSaveException {
        if (FormatUtil.checkStringIsNull(receiveBlog.getTitle(), receiveBlog.getBlog()))
            throw new BlogIsNullException("标题或内容不能为空");
        blog.setUserId(receiveBlog.getUserId());
        blog.setTitle(receiveBlog.getTitle());
        blog.setContent(receiveBlog.getBlog());
        blog.setCommentNum(0);
        blog.setWatchedNum(0);
        blog.setState(1);
        int result = blogDao.saveBlog(blog);
        if (result == 0) {
            throw new BlogSaveException("博客存储失败");
        }
    }

    @Override
    public List<Blog> loadBlogs() {
        List<Blog> blogList = blogDao.selectBlogs();
//            System.out.println(this.getClass().getName() + "\t" + blogList);
        return blogList;
    }

    @Override
    public List<Blog> loadBlogsByPage(Integer pageIndex, Integer blogsPerPage) {
        Integer startIndex = pageIndex * blogsPerPage;
        List<Blog> blogList = blogDao.selectBlogsByPage(startIndex, blogsPerPage);
//            System.out.println(this.getClass().getName() + "\t" + blogList);
        System.out.println("当前时间\t" + LocalDateTime.now());
        LocalDateTime test = blogList.get(0).getCreateDate();
        System.out.println(test);

        return blogList;
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
