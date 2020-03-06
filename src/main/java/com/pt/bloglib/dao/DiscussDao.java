package com.pt.bloglib.dao;

import com.pt.bloglib.dao.entity.Discuss;
import com.pt.bloglib.dao.pojo.LoadDiscussPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DiscussDao {

    Integer saveDiscuss(Discuss discuss);

    List<LoadDiscussPojo> selectDiscussByBlogId(@Param("blogId") String blogId);
}
