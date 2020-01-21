package com.pt.bloglib.Exception;

public class LoadBlogsException extends Exception {

    private String msg;

    public LoadBlogsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
