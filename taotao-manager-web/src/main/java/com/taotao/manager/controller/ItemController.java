package com.taotao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;

@Controller
@RequestMapping("item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	/**
	 * 新增商品
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addItem(Item item,String desc){
		//保存商品
		itemService.saveItem(item,desc);
		return "success";
	} 
	
	/**
	 * 获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public TaoResult queryItemsforPage(@RequestParam(value="page",defaultValue="1") Integer page,@RequestParam(value="rows",defaultValue="30") Integer rows){
		//保存商品
		TaoResult<Item> result = itemService.queryItemsforPage(page,rows);
		return result;
	} 
}
