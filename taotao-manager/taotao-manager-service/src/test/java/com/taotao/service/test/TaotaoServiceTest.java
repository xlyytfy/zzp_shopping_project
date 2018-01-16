package com.taotao.service.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class TaotaoServiceTest {
	
	@Test
	public void test(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.37.161", 7001));
		nodes.add(new HostAndPort("192.168.37.161", 7002));
		nodes.add(new HostAndPort("192.168.37.161", 7003));
		nodes.add(new HostAndPort("192.168.37.161", 7004));
		nodes.add(new HostAndPort("192.168.37.161", 7005));
		nodes.add(new HostAndPort("192.168.37.161", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		
		/*cluster.set("cluster", "Cluster Hello World!");*/
		
		System.out.println("/n/n"+cluster.get("cluster")+"/n/n");
	}
}
