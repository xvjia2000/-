package com.xvjia.cms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.hssf.record.CalcCountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xvjia.cms.domain.User;
import com.xvjia.cms.service.UserService;
import com.xvjia.cms.util.CMSException;
import com.xvjia.cms.util.Md5Util;
import com.xvjia.cms.vo.UserVO;

/**
 * @author xvjia
 * 	时间2019年9月18日
 * 
 */
@Controller
@RequestMapping("passport")
public class PassportController {

	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/passport/login";
	}
	
	
	@GetMapping(value = {"","reg"})
	public String reg() {
		return "passport/reg";
	}
	
	
	
	@PostMapping("reg")
	public String reg(@Valid UserVO userVO,Model model,
			RedirectAttributes redirectAttributes,HttpServletRequest request) throws ParseException {
		
		String parameter = request.getParameter("date1");
		System.out.println(parameter);
		
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Date birthday = dateFormat.parse(parameter);
		
		userVO.setBirthday(birthday);
		
		userVO.setNickname(userVO.getUsername());
		userVO.setLocked(0);
		userVO.setRole("0");
		
		userVO.setCreateTime(new Date());
		userVO.setUpdateTime(new Date());
		
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		userVO.setRepassword(Md5Util.md5Encoding(userVO.getRepassword()));
		
		try {
			userService.insertSelective(userVO);
			redirectAttributes.addFlashAttribute("username",userVO.getUsername());
			return "redirect:/passport/login";
		} catch (CMSException e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		
		
		return "passport/reg";
	}
	
	@GetMapping(value = {"login",""})
	public String login() {
		return "passport/login";
	}
	
	@PostMapping("login")
	public String login(Model model,User user,
			RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		user.setPassword(Md5Util.md5Encoding(user.getPassword()));
		try {
			User user2 = userService.login(user);
			//登录成功后根据角色判断当前登陆人要进入的页面
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user2);
			
			if(user2.getRole().equals("0")) {//普通用户进入个人中心
				return "redirect:/";
			}else {
				return "redirect:/admin";
			}
			
			
			
		} catch (CMSException e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		
		return "passport/login";
	}
	
}
