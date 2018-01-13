package com.taotao.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

	@Autowired
	private ItemDescService itemDescService;
	
	@Override
	public void saveItem(Item item, String desc) {
		//保存商品
		item.setStatus(1);
		super.save(item);
		//保存商品描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.saveSelective(itemDesc);		
	}

	@Override
	public TaoResult<Item> queryItemsforPage(Integer page, Integer rows) {
		//创建返回对象
		TaoResult<Item> result = new TaoResult<>();
		
		//获取查询数据
		List<Item> list = super.queryByPage(page, rows);
		
		//获取查询结果集
		PageInfo<Item> pageInfo = new PageInfo<>(list);
		
		//封装结果
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

}
