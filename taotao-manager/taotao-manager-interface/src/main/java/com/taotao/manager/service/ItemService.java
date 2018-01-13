package com.taotao.manager.service;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;

public interface ItemService extends BaseService<Item>{

	/**
	 * 保存商品
	 * @param item
	 * @param desc
	 */
	void saveItem(Item item, String desc);

	/**
	 * 分页获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	TaoResult<Item> queryItemsforPage(Integer page, Integer rows);
	
}
