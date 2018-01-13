package com.taotao.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;

@Controller
@RequestMapping("content/category")
public class ContentCategoryCategory {

	
	@Autowired
	private ContentCategoryService contentCategoryCategory;

	/**
	 * 内容分类
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ContentCategory> toContentCategory(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<ContentCategory> list = contentCategoryCategory.queryContentCategoryByParentId(parentId);
		return list;
	}

	/**
	 * 新建节点
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ContentCategory add(ContentCategory category) {
		return contentCategoryCategory.saveContentCategory(category);
	}

	/**
	 * 更新节点
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(ContentCategory category) {
		contentCategoryCategory.updateByIdSelective(category);
		return "200";
	}
	
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	@ResponseBody
	public String delete(ContentCategory category){
		//删除当前节点以及子节点
		try {
			contentCategoryCategory.deleteContentCategory(category);
		} catch (Exception e) {
			return null;
		}
		return "success";
	}

}
