package com.pt.bloglib.Exception;

public class UserExistsException extends Exception {

    private String msg;

    public UserExistsException(String msg){
        super(msg);
        this.msg = msg;
    }

}
