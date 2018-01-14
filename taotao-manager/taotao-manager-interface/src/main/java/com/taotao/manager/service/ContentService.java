package com.taotao.manager.service;

import java.util.List;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Content;

public interface ContentService extends BaseService<Content>{

	/**
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	TaoResult<Content> queryContentByCategoryId(Long categoryId, Integer page, Integer rows);

	/**
	 * 根据分类id获取分类内容
	 * @param categoryId
	 * @return
	 */
	List<Content> queryContentByCategoryId(Long categoryId);

}
