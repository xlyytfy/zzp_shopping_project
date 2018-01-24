package com.taotao.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ItemMessageListener implements MessageListener{

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemDescService itemDescService;
	
	//静态页面路径
	@Value("${TAOTAO_ITEM_HTML_PATH}")
	private String TAOTAO_ITEM_HTML_PATH;
	
	public static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Override
	public void onMessage(Message message) {
		//判断消息类型
		if(message instanceof TextMessage){
			try {
				//获取消息
				String json = ((TextMessage) message).getText();
				//如果消息不为空
				if(StringUtils.isNotBlank(json)){
					JsonNode readTree = MAPPER.readTree(json);
					String type = readTree.get("type").asText();
					long itemId = readTree.get("itemId").asLong();
					
					if("save".equals(type)){
						createHtml(itemId);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//构建静态页面
	private void createHtml(long itemId) throws Exception {
		//获取构造器
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		//获取模板
		Template template = configuration.getTemplate("item.ftl");
		
		//封装模板数据
		Map<String, Object> root = new HashMap<>();
		Item item = itemService.queryById(itemId);
		ItemDesc itemDesc = itemDescService.queryById(itemId);
		root.put("item", item);
		root.put("itemDesc", itemDesc);
		
		//设置生成模板路径
		Writer out = new FileWriter(new File(this.TAOTAO_ITEM_HTML_PATH+item.getId()+".html"));
		
		//生成静态页面
		template.process(root, out);
	}

}
