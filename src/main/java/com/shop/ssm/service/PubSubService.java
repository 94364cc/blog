package com.shop.ssm.service;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.PubSub;
import com.shop.ssm.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2019/3/5.
 */
public interface PubSubService {

    Message subscribe(PubSub pubSub);
}
