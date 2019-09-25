package com.xvjia.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xvjia.cms.domain.User;
import com.xvjia.cms.vo.UserVO;

/**
 * @author xvjia 时间2019年9月10日
 * 
 */
public interface UserService {

	PageInfo<User> selects(String name,int pageNum, int pageSize);

	/**
	 *  注册用户
	 * @param record
	 * @return
	 */
	int insertSelective(UserVO record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	/**
	 * @param user
	 * @return
	 */
	User login(User user);

}
