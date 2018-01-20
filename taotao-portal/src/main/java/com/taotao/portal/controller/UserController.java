package com.taotao.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manager.pojo.User;
import com.taotao.manager.service.UserService;
import com.taotao.portal.utils.CookieUtils;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userServcie;
	
	@Value("${TT_TICKET}")
	private String TT_TICKET;
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/doRegister" ,method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doregist(User user){
		try {
			Map<String, Object> result = new HashMap<>();
			int code = userServcie.doregist(user);
			if(200 == code){
				result.put("status", code);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "doLogin",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doLogin(HttpServletRequest request,HttpServletResponse response,User user){
		Map<String,Object> map = new HashMap<>();
		String ticket = userServcie.doLogin(user);
		if(StringUtils.isNotBlank(ticket)){
			CookieUtils.setCookie(request, response,this.TT_TICKET,ticket,24*60*60, true);
			map.put("status", 200);
		}
		return map;
	}

	
}

