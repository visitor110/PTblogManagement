package com.pt.bloglib.controller;

import com.pt.bloglib.Exception.DiscussSaveException;
import com.pt.bloglib.dao.entity.Discuss;
import com.pt.bloglib.dao.pojo.LoadDiscussPojo;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.service.DiscussService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/discuss")
@Api
public class DiscussController {

    @Resource
    private DiscussService discussService;

    @ApiOperation(value = "发表评论")
    @RequestMapping(value = "/sendDiscuss", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result createDiscuss(Discuss discuss) throws DiscussSaveException {
        discussService.saveDiscuss(discuss);
        return new Result(RequestCodeEnum.OK.getState(), "发表成功", null);
    }

    @ApiOperation(value = "加载评论")
    @RequestMapping(value = "/loadDiscussByBlogId", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result loadDiscussByBlogId(String blogId) {
        System.out.println(blogId);
        List<LoadDiscussPojo> list = discussService.loadDiscussByBlogId(blogId);
        return new Result(RequestCodeEnum.OK.getState(), "加载评论成功", list);
    }

}
