package com.pt.bloglib.Exception;

/**
 * 用户注册时 向邮箱发送验证码，若中途改变用户名或验证码错误报错
 */
public class UsernameOrVerifyCodeException extends Exception {

    private String msg;

    public UsernameOrVerifyCodeException(String msg){
        super(msg);
        this.msg = msg;
    }
}
