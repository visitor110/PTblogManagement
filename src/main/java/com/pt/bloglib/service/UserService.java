package com.pt.bloglib.service;

import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.RegisterUser;

public interface UserService {

    User login(String name, String password);
    User findUserByName(String username);
    void register(RegisterUser user);
}
