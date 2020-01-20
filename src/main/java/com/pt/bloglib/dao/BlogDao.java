package com.pt.bloglib.dao;

import com.pt.bloglib.dao.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BlogDao {
    Integer saveBlog(Blog blog);
}
