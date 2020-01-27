package com.pt.bloglib.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagDao {

    Integer saveTag(@Param("tagList") List<String> tagList,
                    @Param("blogId") Integer BlogId);

}
