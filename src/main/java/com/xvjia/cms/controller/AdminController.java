package com.xvjia.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xvjia
 * 	时间2019年9月11日
 * 
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@RequestMapping(value = {"index","/",""})
	public String index () {
		
		return "admin/index";
	}
}
