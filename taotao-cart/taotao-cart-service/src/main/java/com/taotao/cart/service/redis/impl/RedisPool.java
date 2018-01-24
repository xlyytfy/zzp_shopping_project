package com.taotao.cart.service.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.cart.service.redis.RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPool implements RedisUtils {

	@Autowired
	private JedisPool jedisPool;

	@Override
	public void set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, value);
		jedis.close();
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public void set(String key, String value, Integer seconds) {
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, value);
		// 设置超时时间
		jedis.expire(key, seconds);
		jedis.close();
	}

	@Override
	public void exprie(String key, Integer seconds) {
		Jedis jedis = jedisPool.getResource();
		jedis.expire(key, seconds);
		jedis.close();
	}

	@Override
	public void delete(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.del(key);
		jedis.close();
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		//自增
		Long count = jedis.incr(key);
		jedis.close();
		return count;
	}

}
