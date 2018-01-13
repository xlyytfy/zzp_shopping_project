package com.taotao.manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {

	@Override
	public List<ContentCategory> queryContentCategoryByParentId(Long parentId) {
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setParentId(parentId);
		return super.queryListByWhere(contentCategory);
	}

	@Override
	public ContentCategory saveContentCategory(ContentCategory category) {
		//默认状态可以正常
		category.setStatus(1);
		//默认不是parentid
		category.setIsParent(false);
		
		//保存当前节点
		super.save(category);
		
		//获取新建节点的父节点
		ContentCategory parentContentCategory = super.queryById(category.getParentId());
		
		if(!parentContentCategory.getIsParent()){
			//如果父节点不是parent节点
			parentContentCategory.setIsParent(true);
			//更新父节点
			super.updateByIdSelective(parentContentCategory);
		}
		return category;
	}

	@Override
	public void deleteContentCategory(ContentCategory category) {
		//如果当前节点无兄弟节点，设置父节点isParent = false
		ContentCategory parentCategory = new ContentCategory();
		parentCategory.setParentId(category.getParentId());
		int count = super.queryCountByWhere(parentCategory);
		if(count <= 1){
			//无兄弟节点
			parentCategory.setIsParent(false);
			parentCategory.setParentId(null);
			parentCategory.setId(category.getId());
			super.updateByIdSelective(parentCategory);
		}
		
		
		//递归删除当前节点以及子节点
		//获取当前节点的所有子节点
		deleteNode(category);
		
	}

	private void deleteNode(ContentCategory category) {
		//获取当前节点的所有子节点
		ContentCategory node = new ContentCategory();
		node.setParentId(category.getId());
		List<ContentCategory> list = super.queryListByWhere(node);
		if(list!=null && list.size()>=1){
			for (ContentCategory contentCategory : list) {
				deleteNode(contentCategory);
				super.deleteById(contentCategory.getId());
			}
		}
		super.deleteById(category.getId());
	}

}
