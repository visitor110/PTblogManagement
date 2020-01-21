package com.pt.bloglib.dao;

import com.pt.bloglib.dao.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
    Integer saveBlog(Blog blog);
    List<Blog> selectBlogs();
}
