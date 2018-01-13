package com.taotao.manager.service;

import java.util.List;

import com.taotao.manager.pojo.ContentCategory;

public interface ContentCategoryService extends BaseService<ContentCategory>{


	/**
	 * 根据ParentId获取内容分类
	 * @return
	 */
	List<ContentCategory> queryContentCategoryByParentId(Long parentId);

	/**
	 * 创建内容分类
	 * @param category
	 */
	ContentCategory saveContentCategory(ContentCategory category);

	/**
	 * 删除节点以及子节点
	 * @param category
	 */
	void deleteContentCategory(ContentCategory category);

}
