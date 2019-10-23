package com.xvjia.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author xvjia
 * 	时间2019年9月19日
 * 
 */
public class AnminInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Object object = request.getSession().getAttribute("admin");
		if (null==object) {
			response.sendRedirect("/passport/login");
			return false;
		}else{
			return true;
		}
	}
}
