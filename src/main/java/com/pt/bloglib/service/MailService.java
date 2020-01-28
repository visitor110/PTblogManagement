package com.pt.bloglib.service;

import com.pt.bloglib.dto.Result;

public interface MailService {
    /**
     * 发送邮件
     * @param toMail 邮件收件人
     */
    default Result sendVerifyCode(String toMail){return null;}
}
