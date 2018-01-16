package com.taotao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;

@Controller
@RequestMapping("item/interface")
public class RestFulItemController {

	@Autowired
	private ItemService itemServcie;
	
	/**
	 * 根据id获取商品
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ResponseEntity<Item> queryItemById(@PathVariable(value="id") Long id){
		try {
			Item item = itemServcie.queryById(id);
			/*
			 * HttpStatus：枚举类型
			 * HttpStatus.OK : 查询返回http状态 200
			 * HttpStatus.INTERNAL_SERVER_ERROR : 异常返回
			 * CREATED:新增成功返回201
			 * NO_CONTENT：删除和修改成功返回204
			 * body:封装数据，如果返回为void则封装null即为body(null)
			 */
			//return ResponseEntity.status(HttpStatus.OK).body(item);
			
			/*
			 * ok()		:返回200简写
			 * build()	：返回空
			 */
			//return ResponseEntity.ok().build();
			
			return ResponseEntity.ok().body(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果有异常返回500状态码,封装数据为null
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 新增
	 * @param item
	 * @return ResponseEntity<Void> 返回空
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item){
		try {
			itemServcie.save(item);
			//无异常返回201
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//异常返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	/**
	 * 修改
	 * @param item
	 * @return
	 * put和delete请求需要添加过滤器
	 * 
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateItem(Item item){
		try {
			itemServcie.updateByIdSelective(item);
			//无异常返回204
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//异常返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteItem(@PathVariable(value="id") Long id){
		try {
			itemServcie.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
