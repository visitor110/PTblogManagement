package com.pt.bloglib.Exception;

/**
 * 通过token获取用户信息
 * 若redis中没有信息 则报错
 */
public class NoSuchUserInfoException extends Exception{

    private String msg;

    public NoSuchUserInfoException(String msg){
        super(msg);
        this.msg = msg;
    }
}
