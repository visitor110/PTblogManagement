package com.pt.bloglib.service;

import com.pt.bloglib.Exception.*;
import com.pt.bloglib.dao.entity.User;
import com.pt.bloglib.dao.pojo.ChangePasswordUserPojoPojo;
import com.pt.bloglib.dao.pojo.RegisterUserPojo;
import com.pt.bloglib.dao.pojo.UserInfoPojo;

public interface UserService {

//    User login(String name, String password);
    User findUserByName(String username);
    void register(RegisterUserPojo user) throws UserExistsException, UsernameOrVerifyCodeException;
    UserInfoPojo getUserInfoByToken(String token) throws NoSuchUserInfoException;
    void changePassword(ChangePasswordUserPojoPojo user) throws UsernameOrVerifyCodeException, UserNoFoundException, ChangePasswordException;
}
