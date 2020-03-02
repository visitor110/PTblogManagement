package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.UserExistsException;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.utils.FormatUtil;
import com.pt.bloglib.utils.VerifyCodeUtil;
import org.springframework.stereotype.Service;

@Service("register")
public class MailServiceRegister extends MailBaseService {

    @Override
    public void sendVerifyCode(String toMail, String username) throws Exception {

        User foundUser = userDao.findUserByName(username);
        if (!FormatUtil.checkClassIsNull(foundUser))
            throw new UserExistsException("用户名已存在");

        String subject = "PTBlog用户注册验证码";
        String code = VerifyCodeUtil.registerMailVerifyCode(6);
        String content =
                "尊敬的用户\t" + username + "\n" +
                        "您的验证码为\t" + code + "\n" +
                        "有效时间为" + EXPIRE_TIME / 60 + "分钟";
        sendMail(toMail, subject, content);
        String key = username + code + "register";
        redisUtil.set(key, code, EXPIRE_TIME);
    }

}
