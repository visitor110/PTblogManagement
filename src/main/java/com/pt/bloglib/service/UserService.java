package com.pt.bloglib.service;

import com.pt.bloglib.Exception.*;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.ChangePasswordUser;
import com.pt.bloglib.dao.pojo.RegisterUser;
import com.pt.bloglib.dao.pojo.UserInfo;

public interface UserService {

//    User login(String name, String password);
    User findUserByName(String username);
    void register(RegisterUser user) throws UserExistsException, UsernameOrVerifyCodeException;
    UserInfo getUserInfoByToken(String token) throws NoSuchUserInfoException;
    void changePassword(ChangePasswordUser user) throws UsernameOrVerifyCodeException, UserNoFoundException, ChangePasswordException;
}
