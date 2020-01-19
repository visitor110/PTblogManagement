package com.pt.bloglib.Exception;

public class UserNoFoundException extends Exception {

    private String msg;

    public UserNoFoundException(String msg){
        super(msg);
        this.msg = msg;
    }
}
