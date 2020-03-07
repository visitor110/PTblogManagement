package com.pt.bloglib.dao;

import com.pt.bloglib.dao.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReplyDao {
    Integer saveReply(Reply reply);
}
