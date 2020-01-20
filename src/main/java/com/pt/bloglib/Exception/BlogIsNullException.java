package com.pt.bloglib.Exception;

public class BlogIsNullException extends Exception {

    private String msg;

    public BlogIsNullException(String msg){
        super(msg);
        this.msg = msg;
    }
}

