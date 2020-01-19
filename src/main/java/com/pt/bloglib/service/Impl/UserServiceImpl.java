package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.UserExistsException;
import com.pt.bloglib.dao.UserDao;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.RegisterUser;
import com.pt.bloglib.security.utils.UserPasswordEncoder;
import com.pt.bloglib.service.UserService;
import com.pt.bloglib.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private User user;

    @Bean
    public User getUser() {
        return new User();
    }

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
        return user;
    }

    @Override
    public void register(RegisterUser registerUser) throws UserExistsException {
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
