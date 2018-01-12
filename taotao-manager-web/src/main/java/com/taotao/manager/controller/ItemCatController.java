package com.taotao.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("query/{page}")
	@ResponseBody
	public List<ItemCat> queryItemCatByPage(@PathVariable(value="page") Integer page
			,@RequestParam(value="rows") Integer rows){
		//查分页数据
		List<ItemCat> list = itemCatService.queryByPage(page, rows);
		return list;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<ItemCat> queryItemCatsByParentId(@RequestParam(value="id",defaultValue="0") Long parentId){
		System.out.println(parentId);
		List<ItemCat> list = itemCatService.queryItemCatsByParentId(parentId);
		return list;
	}
	
}
