package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.ReplySaveException;
import com.pt.bloglib.dao.ReplyDao;
import com.pt.bloglib.dao.entity.Reply;
import com.pt.bloglib.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyDao replyDao;

    @Override
    public void saveReply(Reply reply) throws ReplySaveException {
        int result = replyDao.saveReply(reply);
        if (result == 0) throw new ReplySaveException("保存回复失败");
    }
}
