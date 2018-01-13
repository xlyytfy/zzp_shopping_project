package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {
	
	/**
	 * 返回输入的页面
	 * @param pageName
	 * @return
	 */
	@RequestMapping("{pageName}")
	public String toPage(@PathVariable(value="pageName") String pageName){
		
		//返回页面
		return pageName;
	}
}
