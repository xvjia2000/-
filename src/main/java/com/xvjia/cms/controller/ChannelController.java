package com.xvjia.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xvjia.cms.domain.Channel;
import com.xvjia.cms.service.ChannelService;

/**
 * @author xvjia
 * 	时间2019年9月17日
 * 
 */
@Controller
@RequestMapping("channel")
public class ChannelController {

	@Autowired
	private ChannelService cService;
	
	@ResponseBody
	@GetMapping("selects")
	public List<Channel> selects(){
		
		System.out.println(cService.selects());
		
		return cService.selects();
	}
	
}
