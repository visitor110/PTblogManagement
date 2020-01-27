package com.pt.bloglib.dao;

import com.pt.bloglib.dao.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagDao {

    Integer saveTag(@Param("tagList") List<String> tagList,
                    @Param("blogId") Integer BlogId);

    List<Tag> selectTagsByBlogId(@Param("blogId")Integer blogId);

}
