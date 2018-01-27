package com.taotao.sso.service.impl;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	@Value("${TICK_INCR}")
	private String TICK_INCR;
	
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
			if(StringUtils.isNotBlank(ticket)){
				String redisStr = redisUtils.get(ticket);
				if(StringUtils.isNotBlank(redisStr)){
					User user = MAPPER.readValue(redisStr, User.class);
					//如果不为空则查询到对象，表明该对象活跃，重置时间 30分钟
					redisUtils.exprie(TAOTAO_USER_TICKET+ticket, 60*30);
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//没有查询到返回null
		return null;
	}
	
	@Override
	public int doregist(User user) {
		if(user == null || !StringUtils.isNotBlank(user.getUsername())){
			//如果用户信息为空
			return 0;
		}
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		//用户密码加密
		String password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(password);
		//插入user用户
		userMapper.insert(user);
		return 200;
	}

	@Override
	public String doLogin(User user) {
		
		//获取用户
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
	    //查询获取用户
		User result = userMapper.selectOne(user);
		if(result!=null){
			String ticket;
			try {
				ticket = this.TAOTAO_USER_TICKET+redisUtils.incr(this.TICK_INCR)+result.getId(); 
				//如果用户不为空
				redisUtils.set(ticket,MAPPER.writeValueAsString(result),
						60*60);
				//返回令牌
				return ticket;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
