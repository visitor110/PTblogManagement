package com.pt.bloglib.service.Impl;

import com.pt.bloglib.service.MailService;
import com.pt.bloglib.utils.RedisUtil;
import com.pt.bloglib.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailServiceImpl implements MailService {

    private final int EXPIRE_TIME = 5 * 60;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisUtil redisUtil;

    //邮件发件人
    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public void sendVerifyCode(String toMail, String username) {

        String subject = "PTBlog用户注册验证码";
        String code = VerifyCodeUtil.registerMailVerifyCode(6);
        String content = "验证码为\t" + code + "\t有效时间为" + EXPIRE_TIME / 60 + "分钟";
        sendMail(toMail, subject, content);
        redisUtil.set(username + code, code, EXPIRE_TIME);
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
