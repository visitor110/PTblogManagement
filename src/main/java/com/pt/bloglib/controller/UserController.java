package com.pt.bloglib.controller;

import com.pt.bloglib.Exception.UserExistsException;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.RegisterUser;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.enums.RequestStatusEnum;
import com.pt.bloglib.dao.pojo.LoginUser;
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
                    "登陆失败", e);
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
        if(FormatUtil.checkClassIsNull(userData)){
            return new Result(RequestCodeEnum.ERROR.getState(), "注册失败", "userData is null");
        }

        try {
            userService.register(userData);
        } catch (UserExistsException e){
            return new Result(RequestCodeEnum.ERROR.getState(), "注册失败", e);
        }
        catch (Exception e) {
            return new Result(RequestCodeEnum.ERROR.getState(), "注册失败", e);
        }
        return new Result(RequestCodeEnum.OK.getState(), "注册成功", null);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
