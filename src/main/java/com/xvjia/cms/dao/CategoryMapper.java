package com.xvjia.cms.dao;

import java.util.List;

import com.xvjia.cms.domain.Category;

public interface CategoryMapper {
	
	List<Category> selectsByChannelId(Integer cid);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}