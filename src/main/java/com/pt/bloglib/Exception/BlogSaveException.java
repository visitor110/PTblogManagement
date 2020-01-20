package com.pt.bloglib.Exception;

public class BlogSaveException extends Exception {

    private String msg;

    public BlogSaveException(String msg) {
        super(msg);
        this.msg = msg;
    }
}