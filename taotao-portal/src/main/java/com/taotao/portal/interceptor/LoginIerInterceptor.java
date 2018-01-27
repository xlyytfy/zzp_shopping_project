package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.manager.pojo.User;
import com.taotao.portal.utils.CookieUtils;
import com.taotao.sso.service.UserService;

public class LoginIerInterceptor implements HandlerInterceptor {

	@Value("${TT_TICKET}")
	private String TT_TICKET;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取当前请求的url
		String url = request.getRequestURL().toString();
		//获取用户ticket
		String ticket = CookieUtils.getCookieValue(request, this.TT_TICKET, true);
		
		//如果ticket不存在则没有登录
		if(!StringUtils.isNotBlank(ticket)){
			//如果未登录则跳转到登录页面
			response.sendRedirect("/page/login.html?url="+url);
			return false;
		}
		
		//ticket存在但是redis不存在用户信息
		User user = userService.querUserByTicket(ticket);
		if(user == null){
			//如果未登录则跳转到登录页面
			response.sendRedirect("/page/login.html?url="+url);
			return false;
		}
		
		//存储用户信息，防止后续多次查询造成资源浪费，拦截器也是一种切面处理
		request.setAttribute("user", user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
