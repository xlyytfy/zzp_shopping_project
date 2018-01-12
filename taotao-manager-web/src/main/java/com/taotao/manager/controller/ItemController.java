package com.taotao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping
	@ResponseBody
	public String addItem(Item item,String desc){
		//保存商品
		item.setStatus(1);
		itemService.saveSelective(item);
		//保存商品描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.saveSelective(itemDesc);
		return "success";
	} 
}
