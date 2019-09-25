package com.xvjia.cms.service;

import java.util.List;

import com.xvjia.cms.domain.Category;
import com.xvjia.cms.domain.Channel;

/**
 * @author xvjia
 * 	时间2019年9月17日
 * 
 */
public interface CategoryService {

	List<Category> selectsByChannelId(Integer cid);
}
