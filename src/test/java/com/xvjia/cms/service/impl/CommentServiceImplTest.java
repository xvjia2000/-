package com.xvjia.cms.service.impl;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xvjia.cms.domain.Comment;
import com.xvjia.cms.service.CommentService;

/**
 * @author xvjia 时间2019年9月21日
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class CommentServiceImplTest {

	@Autowired
	private CommentService commentService;

	@Test
	public void testSelects() {

		List<Comment> list = commentService.selects(null);
		for (Comment comment : list) {
			System.out.println(comment);
		}
	}

}
