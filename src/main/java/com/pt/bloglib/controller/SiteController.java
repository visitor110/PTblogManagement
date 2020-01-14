package com.pt.bloglib.controller;

import com.pt.bloglib.config.SiteIntroductionConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site")
@Api
public class SiteController {

    @Autowired
    private SiteIntroductionConfig siteIntroductionConfig;

    @ApiOperation(value = "site introduction")
    @RequestMapping("/introduction")
    @ResponseBody
    public String test() {
        return siteIntroductionConfig.getIntroduction();
    }


}
