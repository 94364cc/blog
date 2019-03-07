package com.shop.ssm.service.impl;

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

    public Message subscribe(PubSub pubSub) {
        pubSubMapper.subscribe(pubSub);
        return Message.Ok();
    }




}
