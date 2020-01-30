package com.pt.bloglib.service;

import com.pt.bloglib.Exception.ChangePasswordException;
import com.pt.bloglib.Exception.UserExistsException;
import com.pt.bloglib.Exception.UserNoFoundException;
import com.pt.bloglib.Exception.UsernameOrVerifyCodeException;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.ChangePasswordUser;
import com.pt.bloglib.dao.pojo.RegisterUser;

public interface UserService {

    User login(String name, String password);
    User findUserByName(String username);
    void register(RegisterUser user) throws UserExistsException, UsernameOrVerifyCodeException;
    void changePassword(ChangePasswordUser user) throws UsernameOrVerifyCodeException, UserNoFoundException, ChangePasswordException;
}
