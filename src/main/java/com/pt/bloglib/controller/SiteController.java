package com.pt.bloglib.controller;

import com.pt.bloglib.config.SiteIntroductionConfig;
import com.pt.bloglib.dao.entity.Blog;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/site")
@Api
public class SiteController {

    private SiteIntroductionConfig siteIntroductionConfig;

    private BlogService blogService;

    @ApiOperation(value = "site introduction")
    @RequestMapping("/introduction")
    @ResponseBody
    public String test() {
        return siteIntroductionConfig.getIntroduction();
    }

    @ApiOperation(value = "主页加载博客")
    @RequestMapping("/blogList")
    @ResponseBody
    public Result getBlogList(Integer pageIndex,Integer blogsPerPage) {
        System.out.println("pageIndex\t" + pageIndex);
        System.out.println("blogsPerPage\t" + blogsPerPage);
        try {
            List<Blog> lists = blogService.loadBlogsByPage(pageIndex,blogsPerPage);
            return new Result(RequestCodeEnum.OK.getState(), "博客加载成功", lists);
        } catch (Exception e) {
            return new Result(RequestCodeEnum.ERROR.getState(), "博客加载失败", e);
        }
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setSiteIntroductionConfig(SiteIntroductionConfig siteIntroductionConfig) {
        this.siteIntroductionConfig = siteIntroductionConfig;
    }


}
