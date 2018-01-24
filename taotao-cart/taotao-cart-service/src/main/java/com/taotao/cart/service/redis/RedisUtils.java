package com.taotao.cart.service.redis;

public interface RedisUtils {
	
	/**
	 * 设置参数
	 * @param key
	 * @param values
	 */
	public void set(String key,String value);
	
	/**
	 * 根据key值获取参数
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 设置值的同时设置超时时间
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void set(String key,String value,Integer seconds);
	
	/**
	 * 设置key的生存时间，默认一直存在
	 * @param key
	 * @param seconds
	 */
	public void exprie(String key,Integer seconds);
	
	/**
	 * 删除key
	 * @param key
	 */
	public void delete(String key);
	

	/**
	 * value加一
	 * 
	 * @param key
	 * @return
	 */
	public Long incr(String key);
}
