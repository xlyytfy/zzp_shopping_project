package com.taotao.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.mapper.UserMapper;
import com.taotao.manager.pojo.User;
import com.taotao.sso.service.UserService;
import com.taotao.sso.service.redis.RedisUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${TAOTAO_USER_TICKET}")
	private String TAOTAO_USER_TICKET;
	
	//JACKSON
	private static final ObjectMapper MAPPER = new  ObjectMapper();
	
	@Override
	public boolean checkUser(String param, Integer type) {
		User user = new User();
		switch (type) {
		case 1:
			user.setUsername(param);
			break;
		case 2:
			user.setPhone(param);
			break;
		case 3:
			user.setEmail(param);
			break;
		}
		
		int count = userMapper.selectCount(user);

		//如果返回true表示可用，返回false表示不可用
		return count == 0;
	}

	@Override
	public User querUserByTicket(String ticket) {
		try {
			String redisStr = redisUtils.get(ticket);
			if(StringUtils.isNotBlank(redisStr)){
				User user = MAPPER.readValue(redisStr, User.class);
				//如果不为空则查询到对象，表明该对象活跃，重置时间 30分钟
				redisUtils.exprie(TAOTAO_USER_TICKET+ticket, 60*30);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//没有查询到返回null
		return null;
	}

}
