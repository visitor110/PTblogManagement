package com.pt.bloglib.service.Impl;

import com.pt.bloglib.dao.UserDao;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.security.utils.UserPasswordEncoder;
import com.pt.bloglib.service.UserService;
import com.pt.bloglib.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Bean
    public UserPasswordEncoder getEncoder() {
        return new UserPasswordEncoder();
    }

    private UserPasswordEncoder encoder;

    //delete
    @Override
    public User login(String name, String password) {
        User user = userDao.findUserByName(name);
        if (user != null && encoder.matches(password, user.getPassword())) {
            System.out.println("com.pt.bloglib.service.Impl.UserServiceImpl suceess\t"
                    + user.getPassword() + " " + password);
            return user;
        }
        System.out.println("com.pt.bloglib.service.Impl.UserServiceImpl fail\t");
        return null;
    }

    @Override
    public User findUserByName(String username) {
        User user = userDao.findUserByName(username);
        if (!FormatUtil.checkClassIsNull(user)) {
            return user;
        } else {
            System.out.println("user no found");
            return null;
        }

    }

    @Override
    public void registe(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setEncoder(UserPasswordEncoder encoder) {
        this.encoder = encoder;
    }


}
