package com.taotao.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Autowired
	private Destination destination;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	//jackson工具类处理json数据
	private final static ObjectMapper MAPPER = new ObjectMapper();
	
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
		
		//保存商品使用activemq中间间广播，分布通知solr系统，静态化页面系统
		sendMessage("save",item.getId());
	}

	
	
	/**
	 * 消息中间件实现广播
	 * @param string
	 * @param id
	 */
	private void sendMessage(final String type, final Long itemId) {
		/**
		 * destination:设置发送主题以及名称
		 * MessageCreator:发送内容
		 */
		this.jmsTemplate.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				//设置发送消息体
				TextMessage message = new ActiveMQTextMessage();
				
				//封装消息体
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("type", type);
				params.put("itemId", itemId);
				
				//map转换为json
				try {
					String json = MAPPER.writeValueAsString(params);
					//设置消息内容
					message.setText(json);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
				return message;
			}
		});
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
