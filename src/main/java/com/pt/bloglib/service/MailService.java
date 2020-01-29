package com.pt.bloglib.service;

public interface MailService {
    /**
     * 发送邮件
     *
     * @param toMail 邮件收件人
     */
    default void sendVerifyCode(String toMail, String username) throws Exception {
    }
}
