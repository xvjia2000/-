package com.xvjia.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xvjia.cms.dao.CategoryMapper;
import com.xvjia.cms.domain.Category;
import com.xvjia.cms.domain.Channel;
import com.xvjia.cms.service.CategoryService;

/**
 * @author xvjia
 * 	时间2019年9月17日
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Category> selectsByChannelId(Integer cid) {
		return categoryMapper.selectsByChannelId(cid);
	}


}
