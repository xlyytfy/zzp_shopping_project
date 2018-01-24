package com.taotao.cart.service;

import java.util.List;

import com.taotao.manager.pojo.Cart;

public interface CartService {

	/**
	 * 查询订单并且添加到购物车
	 * @param itemId
	 * @param num 
	 * @param long1 
	 * @throws Exception
	 */
	void addItem(Long itemId, Long userId, Integer num) throws Exception;

	/**
	 * 根据用户id获取购物车列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Cart> queryCartByUserId(Long id) throws Exception;

}
