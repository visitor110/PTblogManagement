package com.pt.bloglib.service;

import com.pt.bloglib.Exception.ReplySaveException;
import com.pt.bloglib.dao.entity.Reply;

public interface ReplyService {
    void saveReply(Reply Reply) throws ReplySaveException;
}
