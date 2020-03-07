package com.pt.bloglib.controller;

import com.pt.bloglib.Exception.ReplySaveException;
import com.pt.bloglib.dao.entity.Reply;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/reply")
@Api
public class ReplyController {

    @Resource
    private ReplyService replyService;

    @ApiOperation(value = "发表回复")
    @RequestMapping(value = "/sendReply", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result createDiscuss(Reply reply) throws ReplySaveException {
        replyService.saveReply(reply);
        return new Result(RequestCodeEnum.OK.getState(), "发表成功", null);
    }

}
