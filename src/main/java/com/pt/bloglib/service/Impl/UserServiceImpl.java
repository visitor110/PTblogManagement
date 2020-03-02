package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.*;
import com.pt.bloglib.dao.UserDao;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.ChangePasswordUser;
import com.pt.bloglib.dao.pojo.RegisterUser;
import com.pt.bloglib.dao.pojo.UserInfo;
import com.pt.bloglib.security.utils.UserPasswordEncoder;
import com.pt.bloglib.service.UserService;
import com.pt.bloglib.utils.FormatUtil;
import com.pt.bloglib.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private User user;

    @Resource
    private RedisUtil redisUtil;

    @Bean
    public UserPasswordEncoder getEncoder() {
        return new UserPasswordEncoder();
    }

    private UserPasswordEncoder encoder;

    //delete
//    @Override
//    public User login(String name, String password) {
//        User user = userDao.findUserByName(name);
//        if (user != null && encoder.matches(password, user.getPassword())) {
//            System.out.println("com.pt.bloglib.service.Impl.UserServiceImpl suceess\t"
//                    + user.getPassword() + " " + password);
//            return user;
//        }
//        System.out.println("com.pt.bloglib.service.Impl.UserServiceImpl fail\t");
//        return null;
//    }


    @Override
    public UserInfo getUserInfoByToken(String token) throws NoSuchUserInfoException {
        Object o = redisUtil.get(token);
        UserInfo info = (UserInfo) redisUtil.get(token);

        if (FormatUtil.checkClassIsNull(info)) {
            throw new NoSuchUserInfoException("redis中没有token");
        }
        return info;
    }

    @Override
    public User findUserByName(String username) {
        User user = userDao.findUserByName(username);
        return user;
    }

    @Override
    public void register(RegisterUser registerUser) throws UserExistsException, UsernameOrVerifyCodeException {
        String username = registerUser.getUsername();
        String verifyCode = registerUser.getVerifyCode().toUpperCase();
        if (!redisUtil.hasKey(username + verifyCode + "register"))
            throw new UsernameOrVerifyCodeException("验证码错误");

        user.setUsername(registerUser.getUsername());
        User foundUser = userDao.findUserByName(user.getUsername());
        if (!FormatUtil.checkClassIsNull(foundUser))
            throw new UserExistsException("用户名已存在");

        user.setPassword(encoder.encode(registerUser.getPassword()));
        user.setState(2);
        user.setMail(registerUser.getMail());
        System.out.println("user:\t" + user);
        userDao.addUser(user);
    }

    @Override
    public void changePassword(ChangePasswordUser changePasswordUser) throws
            UsernameOrVerifyCodeException, UserNoFoundException, ChangePasswordException {
        String username = changePasswordUser.getUsername();
        String verifyCode = changePasswordUser.getVerifyCode().toUpperCase();
        if (!redisUtil.hasKey(username + verifyCode + "changePassword"))
            throw new UsernameOrVerifyCodeException("验证码错误");

        User foundUser = userDao.findUserByName(username);
        if (FormatUtil.checkClassIsNull(foundUser))
            throw new UserNoFoundException("用户名不存在");
        String password = encoder.encode(changePasswordUser.getPassword());
        int result = userDao.updatePassword(username, password);
        if (result != 1) {
            throw new ChangePasswordException("密码更新失败");
        }
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setEncoder(UserPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }
}
