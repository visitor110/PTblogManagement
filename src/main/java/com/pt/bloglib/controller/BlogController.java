package com.pt.bloglib.controller;

import com.pt.bloglib.Exception.BlogIsNullException;
import com.pt.bloglib.Exception.BlogSaveException;
import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dao.pojo.ReceiveBlog;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.service.BlogService;
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

    @ApiOperation(value = "主页加载博客")
    @RequestMapping("/blogList")
    @ResponseBody
    public Result getBlogList(Integer pageIndex, Integer blogsPerPage) {
        System.out.println("pageIndex\t" + pageIndex);
        System.out.println("blogsPerPage\t" + blogsPerPage);
        try {
            List<Blog> lists = blogService.loadBlogsByPage(pageIndex, blogsPerPage);
            return new Result(RequestCodeEnum.OK.getState(), "博客加载成功", lists);
        } catch (Exception e) {
            return new Result(RequestCodeEnum.ERROR.getState(), "博客加载失败", e);
        }
    }

    @ApiOperation(value = "发表博客")
    @RequestMapping(value = "/createBlog", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result createBlog(ReceiveBlog receiveBlog) {
        try{
            blogService.saveBlog(receiveBlog);
        }catch (BlogIsNullException e){
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
}
