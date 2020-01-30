package com.pt.bloglib.Exception;

public class ChangePasswordException extends Exception {
    private String msg;
    public ChangePasswordException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
