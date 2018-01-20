package com.taotao.manager.service;

import com.taotao.manager.pojo.User;

public interface UserService {

	/**
	 * 执行注册
	 * @param user
	 * @return
	 */
	int doregist(User user);

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	String doLogin(User user);

}
