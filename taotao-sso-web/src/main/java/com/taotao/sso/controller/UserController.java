package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.pojo.User;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	private final static ObjectMapper MAPPER = new  ObjectMapper();
	
	@RequestMapping(value = "check/{param}/{type}" ,method = RequestMethod.GET)
	public ResponseEntity<String> check(HttpServletRequest request, @PathVariable String param,
			@PathVariable Integer type) {
		String callback = request.getParameter("callback");
		StringBuilder result = new StringBuilder();
		try {
			//判断是否可用
			boolean flag = userService.checkUser(param,type);
			//判断是否跨域调用，如果有callback不为空则表示跨域封装callback()
			if(StringUtils.isNotBlank(callback)){
				result.append(callback+"(").append(flag).append(")");
			}else{
				result.append(flag);
			}
			return ResponseEntity.ok(result.toString());
		} catch (Exception e) {
			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	@RequestMapping(value="{ticket}",method = RequestMethod.GET)
	public ResponseEntity<String> querUserByTicket(HttpServletRequest request,@PathVariable String ticket){
		String callback = request.getParameter("callback");
		StringBuilder result = new StringBuilder();
		try {
			//判断是否可用
			User user = userService.querUserByTicket(ticket);
			//判断是否跨域调用，如果有callback不为空则表示跨域封装callback()
			if(StringUtils.isNotBlank(callback)){
				
				result.append(callback+"(").append(MAPPER.writeValueAsString(user)).append(")");
			}else{
				result.append(MAPPER.writeValueAsString(user));
			}
			return ResponseEntity.ok(result.toString());
		} catch (Exception e) {
			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	

}
