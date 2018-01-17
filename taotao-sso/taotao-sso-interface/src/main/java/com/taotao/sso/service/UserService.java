package com.taotao.sso.service;

import com.taotao.manager.pojo.User;

public interface UserService {

	/**
	 * 判断是否可用用户名或者手机或者邮箱
	 * @param param
	 * @param type 1：用户名  2：手机  3：邮箱
	 * @return
	 */
	boolean checkUser(String param, Integer type);

	/**
	 * 获取redis中对象
	 * @param ticket
	 * @return
	 */
	User querUserByTicket(String ticket);

}
