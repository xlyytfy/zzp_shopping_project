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
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
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
		List<Content> list = contentService.queryContentByCategoryId(categoryId);
		List<HashMap<String, Object>> contentMapList = new ArrayList<>();
		for (Content content : list) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("srcB", content.getPic());
			map.put("height",240);
			map.put("alt","" );
			map.put("width", 670);
			map.put("src", content.getPic2());
			map.put("widthB", 550);
			map.put("href", content.getUrl());
			map.put("heightB", 240);
			contentMapList.add(map);
		}
		String AD = mapper.writeValueAsString(contentMapList);
		model.addAttribute("AD", AD);
		//返回页面
		return "index";
	}
}
