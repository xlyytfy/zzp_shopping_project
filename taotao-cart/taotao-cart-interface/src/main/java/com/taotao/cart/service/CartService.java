package com.taotao.cart.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.manager.pojo.Cart;

public interface CartService {

	/**
	 * 查询订单并且添加到购物车
	 * @param itemId
	 * @param num 
	 * @param long1 
	 * @throws Exception
	 */
	void addCart(Long itemId, Long userId, Integer num) throws Exception;

	/**
	 * 根据用户id获取购物车列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Cart> queryCartByUserId(Long id) throws Exception;

	/**
	 * 修改购物车
	 * @param itemId
	 * @param userId
	 * @param num
	 * @throws Exception
	 */
	void updateCart(Long itemId, Long userId, Integer num) throws Exception;
	
	/**
	 * 删除购物车商品
	 * @param itemId
	 * @param userId
	 * @param num
	 * @throws Exception
	 */
	void deleteItemInCart(Long itemId, Long userId) throws Exception;

	/**
	 * 添加购物车到cookie
	 * @param param 封装request
	 * @param itemId
	 * @param num
	 * @throws Exception
	 */
	void addCookieCart(Map<String, Object> param,Long itemId, Integer num) throws Exception;

	/**
	 * cookie中获取
	 * @param response
	 * @param id
	 */
	List<Cart> queryCookieCartByUserId(Map<String, Object> param);

}
