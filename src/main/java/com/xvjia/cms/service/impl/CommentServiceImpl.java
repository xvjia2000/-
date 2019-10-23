package com.xvjia.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xvjia.cms.dao.ArticleMapper;
import com.xvjia.cms.dao.CommentMapper;
import com.xvjia.cms.domain.Article;
import com.xvjia.cms.domain.Comment;
import com.xvjia.cms.service.CommentService;

/**
 * @author xvjia 时间2019年9月21日
 * 
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Comment> selects(Integer id) {
		return commentMapper.selects(id);
	}

	@Override
	public int insert(Comment comment) {
		
		Article article = new Article();
		
		article.setId(comment.getArticle_id());
		
		
		System.out.println("-------------------"+article);
		int i = articleMapper.updatecomments(article);
		
		
		System.out.println("--------------"+i);
		
		return commentMapper.insert(comment);
	}

}
