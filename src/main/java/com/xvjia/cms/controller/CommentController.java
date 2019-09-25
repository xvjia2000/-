package com.xvjia.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xvjia.cms.domain.Comment;
import com.xvjia.cms.service.ArticleServcie;
import com.xvjia.cms.service.CommentService;

/**
 * @author xvjia 时间2019年9月21日
 * 
 */
@Controller
@RequestMapping("comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@ResponseBody
	@PostMapping("insert")
	public boolean insert(Comment comment,String a) {
		
		System.out.println("--------------"+a);
		System.out.println("------------------"+comment);
		
		return commentService.insert(comment) > 0;
	}

}
