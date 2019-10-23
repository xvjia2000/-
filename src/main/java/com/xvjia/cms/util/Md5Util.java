package com.xvjia.cms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author xvjia
 * 	时间2019年9月19日
 * 
 */
public class Md5Util {

	
	private static String salt = "1dfs1";
	
	
	public static String md5Encoding(String password) {
		return DigestUtils.md5Hex(password+salt);
	}
	
	public static void main(String[] args) {
		String string = md5Encoding("123123");
		String string2 = md5Encoding("123123");
		System.out.println(string);
		System.out.println(string2);
		
	}
	
}
