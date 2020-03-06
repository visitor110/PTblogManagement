package com.pt.bloglib.service.Impl;

import com.pt.bloglib.Exception.DiscussSaveException;
import com.pt.bloglib.dao.DiscussDao;
import com.pt.bloglib.dao.entity.Discuss;
import com.pt.bloglib.dao.pojo.LoadDiscussPojo;
import com.pt.bloglib.service.DiscussService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscussServiceImpl implements DiscussService {

    @Resource
    private DiscussDao discussDao;
    @Override
    public void saveDiscuss(Discuss discuss) throws DiscussSaveException {
        int result = discussDao.saveDiscuss(discuss);
        if(result == 0) throw new DiscussSaveException("保存评论失败");
    }

    @Override
    public List<LoadDiscussPojo> loadDiscussByBlogId(String blogId) {
        List<LoadDiscussPojo> list = discussDao.selectDiscussByBlogId(blogId);
        return list;
    }
}
