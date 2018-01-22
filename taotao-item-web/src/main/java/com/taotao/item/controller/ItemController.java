package com.taotao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	// http://item.taotao.com/item/${item.id }.html
	@RequestMapping(value = "{itemId}", method = RequestMethod.GET)
	public String toItem(@PathVariable("itemId") Long itemId, Model model) {

		// 根据商品id从服务中查询商品数据
		Item item = this.itemService.queryById(itemId);

		// 根据商品id从服务中查询商品描述数据
		ItemDesc itemDesc = this.itemDescService.queryById(itemId);

		// 保存商品数据到模型中
		model.addAttribute("item", item);

		// 保存商品描述数据到模型中
		model.addAttribute("itemDesc", itemDesc);

		return "item";
	}
}