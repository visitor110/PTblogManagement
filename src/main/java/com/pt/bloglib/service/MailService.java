package com.pt.bloglib.service;

public interface MailService {

    default void sendVerifyCode(String toMail, String username) throws Exception {
    }
}
