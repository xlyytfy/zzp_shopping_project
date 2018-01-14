package com.taotao.manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;

@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

	@Override
	public TaoResult<Content> queryContentByCategoryId(Long categoryId, Integer page, Integer rows) {
		//创建分页实现类
		TaoResult<Content> result = new  TaoResult<>();
		
		//设置分页
		PageHelper.startPage(page, rows);
		
		//查询数据根据categoryId
		Content content = new Content();
		content.setCategoryId(categoryId);
		List<Content> list = super.queryListByWhere(content);
		
		//获取分页工具类
		PageInfo<Content> pageInfo = new PageInfo<>(list); 
		
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public List<Content> queryContentByCategoryId(Long categoryId) {
		//查询数据根据categoryId
		Content content = new Content();
		content.setCategoryId(categoryId);
		List<Content> list = super.queryListByWhere(content);
		return list;
	}

}

