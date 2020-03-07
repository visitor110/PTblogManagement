package com.pt.bloglib.Exception;

public class ReplySaveException extends Exception {

    private String msg;

    public ReplySaveException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
