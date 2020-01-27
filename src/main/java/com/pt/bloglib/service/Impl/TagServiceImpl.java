package com.pt.bloglib.service.Impl;

import com.pt.bloglib.dao.TagDao;
import com.pt.bloglib.dao.entity.Tag;
import com.pt.bloglib.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagDao tagDao;
    @Override
    public Integer saveTags(List<String> tagList,Integer blogId) {
        int result = tagDao.saveTag(tagList,blogId);
        return result;
    }

    @Override
    public List<Tag> selectTagsByBlogId(Integer blogId) {
        List<Tag> tagList = tagDao.selectTagsByBlogId(blogId);
        return tagList;
    }

    @Autowired
    public void setTagDao(TagDao tagDao){this.tagDao = tagDao;}
}
