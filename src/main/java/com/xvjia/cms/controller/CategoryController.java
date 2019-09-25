package com.xvjia.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xvjia.cms.domain.Category;
import com.xvjia.cms.service.CategoryService;

/**
 * @author xvjia
 * 	时间2019年9月17日
 * 
 */
@Controller
@RequestMapping("category")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@GetMapping("selects")
	public List<Category> selectsByChannelId(Integer cid) {
		return categoryService.selectsByChannelId(cid);
	}
	
}
