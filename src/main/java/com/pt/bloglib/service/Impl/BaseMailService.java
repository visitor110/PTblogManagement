package com.pt.bloglib.service.Impl;

import com.pt.bloglib.dao.UserDao;
import com.pt.bloglib.service.MailService;
import com.pt.bloglib.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseMailService implements MailService {

    protected final int EXPIRE_TIME = 5 * 60;

    @Resource
    protected UserDao userDao;

    @Resource
    protected RedisUtil redisUtil;

    @Autowired
    protected JavaMailSender mailSender;

    //邮件发件人
    @Value("${mail.fromMail.addr}")
    private String from;

    public void sendVerifyCode(String toMail, String username) throws Exception {
    }

    public void sendMail(String to, String subject, String content) {
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
