package com.taotao.sso.service.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.sso.service.redis.RedisUtils;

import redis.clients.jedis.JedisCluster;

public class RedisCluster implements RedisUtils{

	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public void set(String key, String value) {
		jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public void set(String key, String value, Integer seconds) {
		jedisCluster.set(key, value);
		// 设置超时时间
		jedisCluster.expire(key, seconds);
	}

	@Override
	public void exprie(String key, Integer seconds) {
		jedisCluster.expire(key, seconds);
	}

	@Override
	public void delete(String key) {
		jedisCluster.del(key);
	}

	@Override
	public Long incr(String key) {
		//自增
		Long count = jedisCluster.incr(key);
		return count;
	}


}
