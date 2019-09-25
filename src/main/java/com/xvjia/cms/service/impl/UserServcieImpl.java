package com.xvjia.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xvjia.cms.dao.UserMapper;
import com.xvjia.cms.domain.User;
import com.xvjia.cms.service.UserService;
import com.xvjia.cms.util.CMSException;
import com.xvjia.cms.vo.UserVO;

/**
 * @author xvjia
 * 	时间2019年9月10日
 * 
 */
@Service
public class UserServcieImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public PageInfo<User> selects(String name,int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		List<User> selects = userMapper.selects(name);
		
		PageInfo<User> pageInfo = new PageInfo<User>(selects);
		
		
		return pageInfo;
	}

	@Override
	public int insertSelective(UserVO record) {
		
//		判斷用戶的注冊信息是否符合要求
		
		
//		1.兩次密碼是否一致
		
		if (!record.getPassword().equals(record.getRepassword())) {
			throw new CMSException("兩次密碼不一致");
		}

		User user = userMapper.selectByUsername(record.getUsername());
		if (user!=null) {
			throw new CMSException("用戶已存在");
		}
		
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User login(User user) {
		
		if(user.getUsername()==null)
			throw new CMSException("用戶名不能爲空");
		if(user.getPassword()==null)
			throw new CMSException("密碼不能爲空");
		User user2 = userMapper.login(user);
		if(user2==null)
			throw new CMSException("用戶名不存在");
		if(!user.getPassword().equals(user2.getPassword()))
			throw new CMSException("密碼錯誤");
		if (user2.getLocked()==1) {
			throw new CMSException("用戶已禁用");
		}
		
		return user2;
	}

}
