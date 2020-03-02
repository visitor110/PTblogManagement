package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.UserExistsException;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.utils.FormatUtil;
import com.pt.bloglib.utils.VerifyCodeUtil;
import org.springframework.stereotype.Service;

@Service("changePassword")
public class MailServiceChangePassword extends MailBaseService {

    @Override
    public void sendVerifyCode(String toMail, String username) throws Exception {

        User foundUser = userDao.findUserByName(username);
        if (FormatUtil.checkClassIsNull(foundUser))
            throw new UserExistsException("用户不存在");

        String subject = "PTBlog用户修改密码验证码";
        String code = VerifyCodeUtil.registerMailVerifyCode(6);
        String content =
                "尊敬的用户\t" + username + "\n" +
                        "您的验证码为\t" + code + "\n" +
                        "有效时间为" + EXPIRE_TIME / 60 + "分钟";
        sendMail(toMail, subject, content);
        String key = username + code + "changePassword";
        redisUtil.set(key, code, EXPIRE_TIME);
    }
}
