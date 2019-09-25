package com.xvjia.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xvjia
 * 	时间2019年9月17日
 * 
 */
@Controller
@RequestMapping("my")
public class MyController {

	@GetMapping(value = {"index","","/"})
	public String index() {
		return "my/index";
	}
}
