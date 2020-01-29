package com.pt.bloglib.controller;

import com.pt.bloglib.Exception.UserExistsException;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.LoginUser;
import com.pt.bloglib.dao.pojo.RegisterUser;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.enums.RequestStatusEnum;
import com.pt.bloglib.service.MailService;
import com.pt.bloglib.service.UserService;
import com.pt.bloglib.utils.FormatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    private MailService mailService;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result doLogin(LoginUser userData) {
        System.out.println("doLogin\t" + userData.toString());
        User user = null;
        try {
            user = userService.login(userData.getUsername(), userData.getPassword());
            System.out.println("login result:\t" + user.toString());
        } catch (NullPointerException e) {
            return new Result(RequestStatusEnum.LOGINERROR.getState(),
                    "数据为空", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(RequestStatusEnum.OK.getState(), "登陆成功", null);
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result doRegister(RegisterUser userData) {
        System.out.println("doRegister\t" + userData.toString());
        if (FormatUtil.checkClassIsNull(userData)) {
            return new Result(RequestCodeEnum.ERROR.getState(), "信息不全", "userData is null");
        }
        try {
            userService.register(userData);
        } catch (UserExistsException e) {
            return new Result(RequestCodeEnum.ERROR.getState(), "用户名已存在", e);
        } catch (Exception e) {
            return new Result(RequestCodeEnum.ERROR.getState(), "注册失败", e);
        }
        return new Result(RequestCodeEnum.OK.getState(), "注册成功", null);
    }

    @ApiOperation(value = "用户邮箱注册验证码")
    @RequestMapping(value = "/mailVerifyCode", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result sendMailVerifyCode(@RequestParam("mail") String toMail,
                                     @RequestParam("username") String username) {
        try {
            mailService.sendVerifyCode(toMail, username);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(RequestCodeEnum.ERROR.getState(), "邮件发送失败", e);
        }
        return new Result(RequestCodeEnum.OK.getState(), "邮件发送成功", null);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
