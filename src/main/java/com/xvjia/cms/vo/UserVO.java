package com.xvjia.cms.vo;

import java.io.Serializable;

import com.xvjia.cms.domain.User;

/**
 * @author xvjia
 * 	时间2019年9月18日
 * 
 */
public class UserVO extends User{

	
	/**
	 * name: xvjia
	 */
	private static final long serialVersionUID = -111212752485513121L;
	private String repassword;//重复密码

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
