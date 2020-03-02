package com.pt.bloglib.controller;

import com.pt.bloglib.Exception.BlogIsNullException;
import com.pt.bloglib.Exception.BlogSaveException;
import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dao.entity.Tag;
import com.pt.bloglib.dao.pojo.ReceiveBlog;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.service.BlogService;
import com.pt.bloglib.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@Api
public class BlogController {

    private BlogService blogService;
    private TagService tagService;

    @ApiOperation(value = "主页加载博客")
    @RequestMapping("/blogList")
    @ResponseBody
    public Result getBlogList(Integer pageIndex, Integer blogsPerPage) {
        try {
            List<Blog> lists = blogService.loadBlogsByPage(pageIndex, blogsPerPage);
            lists.forEach(blog -> {
                List<Tag> tagList = tagService.selectTagsByBlogId(blog.getId());
                blog.setTagList(tagList);
            });
            return new Result(RequestCodeEnum.OK.getState(), "博客加载成功", lists);
        } catch (Exception e) {
            return new Result(RequestCodeEnum.ERROR.getState(), "博客加载失败", e);
        }
    }

    /**
     * 动态加载博客
     *
     * @param blogId
     * @return
     */
    @ApiOperation(value = "动态加载博客")
    @RequestMapping(value = "/id/{blogId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getBlogById(@PathVariable String blogId) {
        Integer id = Integer.parseInt(blogId);
        Blog blog = blogService.loadBlogById(id);
        return new Result(RequestCodeEnum.OK.getState(), "收到", blog);
    }

    @ApiOperation(value = "发表博客")
    @RequestMapping(value = "/createBlog", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result createBlog(ReceiveBlog receiveBlog) {
        try {
            blogService.saveBlog(receiveBlog);
        } catch (BlogIsNullException e) {
            return new Result(RequestCodeEnum.ERROR.getState(), e.getMessage(), e);
        } catch (BlogSaveException e) {
            return new Result(RequestCodeEnum.ERROR.getState(), e.getMessage(), e);
        }
        return new Result(RequestCodeEnum.OK.getState(), "发表成功", null);
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
