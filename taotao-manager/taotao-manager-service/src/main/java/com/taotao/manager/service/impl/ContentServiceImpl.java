package com.taotao.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;

import redis.clients.jedis.JedisCluster;

@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Value("${REDIS_CATCH_AD}")
	private String REDIS_CATCH_AD;
	
	@Autowired
	private JedisCluster JedisCluster;
	
	
	@Override
	public TaoResult<Content> queryContentByCategoryId(Long categoryId, Integer page, Integer rows) {
		//创建分页实现类
		TaoResult<Content> result = new  TaoResult<>();
		
		//设置分页
		PageHelper.startPage(page, rows);
		
		//查询数据根据categoryId
		Content content = new Content();
		content.setCategoryId(categoryId);
		List<Content> list = super.queryListByWhere(content);
		
		//获取分页工具类
		PageInfo<Content> pageInfo = new PageInfo<>(list); 
		
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public String queryContentByCategoryId(Long categoryId) {
		//设置redis缓存的数据
		String redisAD = JedisCluster.get("REDIS_CATCH_AD");
		
		//返回redis缓存的数据
		if(StringUtils.isNotBlank(redisAD)){
			return redisAD;
		}
		
		//查询数据根据categoryId
		Content contentParam = new Content();
		contentParam.setCategoryId(categoryId);
		List<Content> list = super.queryListByWhere(contentParam);
		
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
		
		String AD = "";
		
		try {
			AD = mapper.writeValueAsString(contentMapList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(StringUtils.isNoneBlank(AD)){
			try {
				JedisCluster.set("REDIS_CATCH_AD", AD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return AD;
	}

}

