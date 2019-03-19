package com.shop.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.shop.ssm.dao.JedisClient;
import com.shop.ssm.mapper.PubSubMapper;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.PubSub;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.PubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/3/5.
 */
@Service
public class PubSubServiceImpl implements PubSubService{

    @Autowired
    public PubSubMapper pubSubMapper;
    @Autowired
    public JedisClient jedisClient;
    public String SUBSCRIBE="subscribe";

    /**
     * 订阅
     * @param pubId
     * @param subId
     * @return
     */
    public Message subscribe(Integer pubId,Integer subId) {
        pubSubMapper.subscribe(pubId,subId);
        jedisClient.sadd(SUBSCRIBE+pubId,subId.toString());
        return Message.Ok();
    }

    public Message unsubscribe(Integer pubId,Integer subId){
        pubSubMapper.unsubscribe(pubId, subId);
        jedisClient.srem(SUBSCRIBE+pubId,subId.toString());
        return Message.Ok();
    }
    public List<Integer> getSubsByPubId(Integer pubId){
        return pubSubMapper.getSubsByPubId(pubId);
    }
}
