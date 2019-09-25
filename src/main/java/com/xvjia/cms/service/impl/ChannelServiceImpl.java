package com.xvjia.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xvjia.cms.dao.ChannelMapper;
import com.xvjia.cms.domain.Channel;
import com.xvjia.cms.service.ChannelService;

/**
 * @author xvjia
 * 	时间2019年9月17日
 * 
 */
@Service

public class ChannelServiceImpl implements ChannelService{

	@Autowired
	private ChannelMapper channelMapper;
	
	@Override
	public List<Channel> selects() {
		return channelMapper.selects();
	}

}
