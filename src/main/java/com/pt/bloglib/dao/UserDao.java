package com.pt.bloglib.dao;

import com.pt.bloglib.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    User findUserByName(@Param("username") String name);

    void addUser(User user);
}
