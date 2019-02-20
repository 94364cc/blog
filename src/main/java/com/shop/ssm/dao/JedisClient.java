package com.shop.ssm.dao;

import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/10/21.
 */
public interface JedisClient {
    String get(String key);
    String set(String key,String value);
    String hget(String hkey,String key);
    long hset(String hkey,String key,String value);
    long incr(String key);
    long expire(String key,int second);
    long ttl(String key);
    long del(String key);
    long hdel(String heky,String key);
    void setObject(String key,Object object);
    long lpush(String key,String value);
    List<String> lrange(String key,long start,long end);
    long sadd(String key,String value);
    List<String> brpop(String key,int time);
    void zadd(String key,double score,String value);
    double zincrby(String key,double score,String value);
    Transaction muti();
    long publish(String channel,String message);
    void subscribe(JedisPubSub listener,String channel);
    Set keys(String pattern);


}
