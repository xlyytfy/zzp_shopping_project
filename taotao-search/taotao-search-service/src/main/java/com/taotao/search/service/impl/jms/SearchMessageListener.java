package com.taotao.search.service.impl.jms;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.search.service.SearchService;

/**
 * solr系统监听topic-taotao广播
 * 
 * @author Administrator
 *
 */
public class SearchMessageListener implements MessageListener {

	@Autowired
	private SearchService searchService;

	//jackson工具类处理json数据
	private final static ObjectMapper MAPPER = new ObjectMapper();
	
	@Override
	public void onMessage(Message message) {
		// 如果属于text类型消息
		if (message instanceof TextMessage) {
			// 获取消息内容
			try {
				String text = ((TextMessage) message).getText();
				//如果消息不为空
				if(StringUtils.isNotBlank(text)){
					JsonNode readTree = MAPPER.readTree(text);
					if("save".equals(readTree.get("type").asText())){
						searchService.addItem(readTree.get("itemId").asLong());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
