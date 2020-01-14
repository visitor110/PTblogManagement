package com.pt.bloglib.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class HomeController {

    @ApiOperation(value = "主页")
    @RequestMapping(value = "/home")
    @CrossOrigin
    @ResponseBody
    public Object home() {
        return "home page";
    }
}
