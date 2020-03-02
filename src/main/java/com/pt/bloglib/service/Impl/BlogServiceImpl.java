package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.BlogIsNullException;
import com.pt.bloglib.Exception.BlogSaveException;
import com.pt.bloglib.dao.BlogDao;
import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dao.pojo.ReceiveBlog;
import com.pt.bloglib.service.BlogService;
import com.pt.bloglib.service.TagService;
import com.pt.bloglib.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private Blog blog;
    private BlogDao blogDao;
    private TagService tagService;

    @Override
    @Transactional

    public void saveBlog(ReceiveBlog receiveBlog) throws BlogIsNullException, BlogSaveException {
        if (FormatUtil.checkStringIsNull(receiveBlog.getTitle(), receiveBlog.getBlog()))
            throw new BlogIsNullException("标题或内容不能为空");
        blog.setUserId(receiveBlog.getUserId());
        blog.setTitle(receiveBlog.getTitle());
        blog.setContent(receiveBlog.getBlog());
        blog.setCommentNum(0);
        blog.setWatchedNum(0);
        blog.setState(1);
        int blogResult = blogDao.saveBlog(blog);
        if (blogResult == 0) {
            throw new BlogSaveException("博客存储失败");
        }
        int tagResult = tagService.saveTags(receiveBlog.getTagList(), blog.getId());
        if (tagResult == 0) {
            throw new BlogSaveException("tag存储失败");
        }
    }

    @Override
    public List<Blog> loadBlogsByPage(Integer pageIndex, Integer blogsPerPage) {
        Integer startIndex = pageIndex * blogsPerPage;
        List<Blog> blogList = blogDao.selectBlogsByPage(startIndex, blogsPerPage);
        return blogList;
    }

    @Override
    public Blog loadBlogById(Integer blogId) {
        return blogDao.selectBlogById(blogId);
    }

    @Autowired
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Autowired
    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
