package com.pt.bloglib.controller;

import com.pt.bloglib.Exception.NoSuchUserInfoException;
import com.pt.bloglib.dao.pojo.ChangePasswordUser;
import com.pt.bloglib.dao.pojo.RegisterUser;
import com.pt.bloglib.dao.pojo.UserInfo;
import com.pt.bloglib.dto.Result;
import com.pt.bloglib.enums.RequestCodeEnum;
import com.pt.bloglib.enums.RequestStatusEnum;
import com.pt.bloglib.service.Impl.MailServiceStrategy;
import com.pt.bloglib.service.UserService;
import com.pt.bloglib.utils.FormatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private MailServiceStrategy mailServiceStrategy;

//    @ApiOperation(value = "用户登录")
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @CrossOrigin
//    @ResponseBody
//    public Result doLogin(LoginUser userData) {
//        System.out.println("doLogin\t" + userData.toString());
//        User user = null;
//        try {
//            user = userService.login(userData.getUsername(), userData.getPassword());
//            System.out.println("login result:\t" + user.toString());
//        } catch (NullPointerException e) {
//            return new Result(RequestStatusEnum.LOGINERROR.getState(),
//                    "数据为空", e);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new Result(RequestStatusEnum.OK.getState(), "登陆成功", null);
//    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/getUserInfoByToken", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result getRoles(@RequestParam("token")String token) throws NoSuchUserInfoException {
        UserInfo info = userService.getUserInfoByToken(token);
        System.out.println("getinfo\t" + info);
        return new Result(RequestStatusEnum.OK.getState(), "用户信息获取成功", info);
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
        } catch (Exception e) {
            return new Result(RequestCodeEnum.ERROR.getState(), "注册失败", e);
        }
        return new Result(RequestCodeEnum.OK.getState(), "注册成功", null);
    }

    @ApiOperation(value = "发送验证码至邮箱")
    @RequestMapping(value = "/mailVerifyCode", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result sendMailVerifyCode(@RequestParam("mail") String toMail,
                                     @RequestParam("username") String username,
                                     @RequestParam("strategy") String strategy) {
        try {
            mailServiceStrategy.getMailServiceImpl(strategy).sendVerifyCode(toMail, username);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(RequestCodeEnum.ERROR.getState(), "邮件发送失败", e);
        }
        return new Result(RequestCodeEnum.OK.getState(), "邮件发送成功", null);
    }

    @ApiOperation(value = "通过邮箱更改密码")
    @RequestMapping(value = "/changePasswordByMail", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public Result changePasswordByMail(ChangePasswordUser userData) {
        try {
            userService.changePassword(userData);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(RequestCodeEnum.ERROR.getState(), "邮件发送失败", e);
        }
        return new Result(RequestCodeEnum.OK.getState(), "邮件发送成功", null);
    }

}
