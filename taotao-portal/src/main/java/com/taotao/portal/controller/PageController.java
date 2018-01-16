package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;

@Controller
@RequestMapping("index")
public class PageController {
	
	@Value("${TAOTAO_AD_ID}")
	private Long categoryId;
	
	
	
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 返回输入的页面
	 * @param pageName
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toPage(Model model) throws Exception{
		//大广告数据
		String AD = contentService.queryContentByCategoryId(categoryId);
		
		
		
		model.addAttribute("AD", AD);
		//返回页面
		return "index";
	}
}
