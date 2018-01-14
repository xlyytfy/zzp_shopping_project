package com.taotao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;

@Controller
@RequestMapping("content")
public class ContentCategory {
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 根据分类ID分页获取商品内容
	 * @param categoryId 分类id
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public TaoResult<Content> queryContentByCategoryId(Long categoryId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows) {
		TaoResult<Content> result = contentService.queryContentByCategoryId(categoryId, page, rows);
		return result;
	}
	
	/**
	 * 添加内容
	 * @param content
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void addContent(Content content){
		contentService.save(content);
	}
}
