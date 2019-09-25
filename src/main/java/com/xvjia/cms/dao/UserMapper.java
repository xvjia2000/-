package com.xvjia.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xvjia.cms.domain.User;

public interface UserMapper {
	/**
	 * 
	 * @param name
	 * @return
	 */
	List<User> selects(@Param("name")String name);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User selectByUsername(String Username);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	/**
	 * @param user
	 * @return
	 */
	User login(User user);
}