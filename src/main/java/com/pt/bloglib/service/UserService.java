package com.pt.bloglib.service;

import com.pt.bloglib.dao.entity.User;

public interface UserService {

    User login(String name, String password);
    User findUserByName(String username);
    void registe(User user);
}
