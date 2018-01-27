package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping("{pageName}")
	public String page(@PathVariable String pageName,String url,Model model){
		//添加上一次访问url
		model.addAttribute("url", url);
		
		return pageName;
	}
}
