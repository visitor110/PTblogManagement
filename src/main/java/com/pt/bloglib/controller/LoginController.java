package com.pt.bloglib.controller;

import com.pt.bloglib.dao.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api
public class LoginController {

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public Object test(User user) {
        System.out.println(user);
        return user;
    }
}
