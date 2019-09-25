package com.xvjia.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xvjia.cms.domain.Comment;

/**
 * @author xvjia
 * 	时间2019年9月21日
 * 
 */
public interface CommentMapper {

	List<Comment> selects(@Param("id")Integer id);
	
	int insert(Comment comment);
	
}
