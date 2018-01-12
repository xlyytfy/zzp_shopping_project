package com.taotao.manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;

@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {

	@Override
	public List<ItemCat> queryItemCatsByParentId(Long parentId) {
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		List<ItemCat> list = super.queryListByWhere(itemCat);
		return list;
	}

}
