package com.pt.bloglib.Exception;

public class DiscussSaveException extends Exception {

    private String msg;

    public DiscussSaveException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
