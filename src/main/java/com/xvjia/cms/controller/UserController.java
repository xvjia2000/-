package com.xvjia.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xvjia.cms.domain.User;
import com.xvjia.cms.service.UserService;

/**
 * @author xvjia
 * 	时间2019年9月11日
 * 
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("selects")
	public String selects(Model model,@RequestParam(defaultValue  = "")String name,
			@RequestParam(defaultValue = "1")int pageNum , 
			@RequestParam(defaultValue = "3")int pageSize) {
		
		PageInfo<User> info = userService.selects(name, pageNum, pageSize);
		List<User> list = info.getList();
		
		model.addAttribute("users", list);
		model.addAttribute("name", name);
		model.addAttribute("page", info);
		
		
		
		return "admin/users";
	}
	
	@ResponseBody
	@RequestMapping("updateLocked")
	public boolean updateLocked(User user) {
//		System.out.println("user-----"+user);
		
		user.setLocked(user.getLocked()==1?0:1);
		
		return userService.updateByPrimaryKeySelective(user)>0;
	}
	
}
