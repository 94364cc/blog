package com.shop.ssm.dao.impl;

import com.shop.ssm.dao.JedisClient;
import com.shop.ssm.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Set;

public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	public String get(String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		String str=jedis.get(key);
		jedis.close();
		return str;
	}

	public String set(String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		String str=jedis.set(key,value);
		jedis.close();
		return str;
	}

	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		String str=jedis.hget(hkey,key);
		jedis.close();
		return str;
	}

	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result=jedis.hset(hkey,key,value);
		jedis.close();
		return result;
	}

	public long incr(String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result =jedis.incr(key);
		jedis.close();
		return result;
	}

	public long expire(String key, int second) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result =jedis.expire(key, second);
		return result;
	}

	public long ttl(String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result=jedis.ttl(key);
		return result;
	}

	public long del(String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result=jedis.del(key);
		jedis.close();
		return result;
	}

	public long hdel(String heky, String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result=jedis.hdel(heky,key);
		jedis.close();
		return result;
	}

	public void setObject(String key, Object object) {
		Jedis jedis=jedisPool.getResource();
		jedis.set(key.getBytes(), Util.serialize(object));
		jedis.close();
	}

	public long lpush(String key, String value) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.lpush(key,value);
		jedis.close();
		return result;
	}

	public List<String> lrange(String key,long start, long end) {
		Jedis jedis=jedisPool.getResource();
		List<String> result=jedis.lrange(key, start , end );
		return result;
	}

	public long sadd(String key, String value) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.sadd(key,value);
		jedis.close();
		return result;
	}

	public List<String> brpop(String key, int time) {
		Jedis jedis=jedisPool.getResource();
		List<String> list=jedis.brpop(time,key);
		jedis.close();
		return list;
	}

	public void zadd(String key, double score, String value) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.zadd(key, score, value);
		jedis.close();
	}

	public double zincrby(String key, double score, String value) {
		Jedis jedis=jedisPool.getResource();
		double result= jedis.zincrby(key, score, value);
		return result;
	}

	public Transaction muti() {
		Jedis jedis=jedisPool.getResource();
		Transaction t=jedis.multi();
		return t;
	}

	public long publish(String channel, String message) {
		Jedis jedis=jedisPool.getResource();
		long result =jedis.publish(channel,message);
		return result;
	}

	public void subscribe(JedisPubSub listener, String channel) {
		Jedis jedis =jedisPool.getResource();
		jedis.subscribe(listener,channel);
		jedis.close();
	}

	public Set keys(String pattern) {
		Jedis jedis =jedisPool.getResource();
		return jedis.keys(pattern);
	}

}
