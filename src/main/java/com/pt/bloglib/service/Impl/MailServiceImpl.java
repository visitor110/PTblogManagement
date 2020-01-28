package com.pt.bloglib.service.Impl;

import com.pt.bloglib.dto.Result;
import com.pt.bloglib.service.MailService;
import com.pt.bloglib.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    //邮件发件人
    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public Result sendVerifyCode(String toMail){
        String subject = "PTBlog用户注册验证码";
        String content = VerifyCodeUtil.registerMailVerifyCode(6);

        sendMail(toMail,subject,content);
        return null;
    }

    public void sendMail(String to,String subject,String content) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 邮件发送者，这里不能随便填写，必须是真实的发送邮件的邮箱名称
        mailMessage.setFrom(from);
        // 邮件接受者
        mailMessage.setTo(to);
        // 邮件主题
        mailMessage.setSubject(subject);
        // 邮件内容
        mailMessage.setText(content);
        // 发送邮件
        mailSender.send(mailMessage);
    }

}
