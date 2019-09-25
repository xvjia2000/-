package com.xvjia.cms.service;

import java.util.List;

import com.xvjia.cms.domain.Comment;

/**
 * @author xvjia 时间2019年9月21日
 * 
 */
public interface CommentService {

	List<Comment> selects(Integer id);

	int insert(Comment comment);

	/**
	 * @return
	 */
}
