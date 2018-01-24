package com.taotao.search.service;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;

public interface SearchService {

	/**
	 * 获取索引并分页
	 * @param page
	 * @param rows
	 * @param query
	 * @return
	 */
	TaoResult<Item> searchItems(Integer page, Integer rows, String query) throws Exception;

	/**
	 * 添加索引
	 * @param asLong
	 */
	void addItem(long itemId) throws Exception;

}
