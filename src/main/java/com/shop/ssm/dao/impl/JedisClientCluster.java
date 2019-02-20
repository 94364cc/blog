package com.shop.ssm.dao.impl;

import com.shop.ssm.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;


import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;

import java.util.List;

public class JedisClientCluster {


	@Autowired
	private JedisCluster jedisCluster;

	public String get(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.get(key);
	}

	public String set(String key, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.set(key, value);
	}

	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		return jedisCluster.hget(hkey, key);
	}

	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.hset(hkey, key, value);
	}

	public long incr(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.incr(key);
	}

	public long expire(String key, int second) {
		// TODO Auto-generated method stub
		return jedisCluster.expire(key, second);
	}

	public long ttl(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.ttl(key );
	}

	public long del(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.del(key);
	}

	public long hdel(String heky,String key) {
		// TODO Auto-generated method stub
		return jedisCluster.hdel(heky, key);
	}

	public void setObject(String key, Object object) {
//		return jedisCluster
	}

	public long lpush(String key, String value) {
		return 0;
	}

	public List<String> lrange(String key, long start, long end) {
		return null;
	}

	public long sadd(String key, String value) {
		return 0;
	}

	public List<String> brpop(String key, int time) {
		return null;
	}

	public void zadd(String key, double score, String value) {

	}

	public double zincrby(String key, double score, String value) {
		return 0;
	}

	public Transaction muti() {
		return null;
	}

	public long publish(String channel, String message) {
		return 0;
	}

	public void subscribe(JedisPubSub listener, String channel) {

	}

	public String lpop(String key, String value) {
		return null;
	}

}
