package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.PubSub;
import com.shop.ssm.service.PubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/3/17.
 */
@RestController
public class PubSubController {

    @Autowired
    public PubSubService pubSubService;

    @RequestMapping(value = "/pubSub",method = RequestMethod.POST)
    public Message insertPubSub(Integer pubId,HttpSession session){
        Integer subId= session.getAttribute("id")==null ? 2: (Integer) session.getAttribute("id");
        return pubSubService.subscribe(pubId,subId);
    }

    @RequestMapping(value = "/pubSub",method = RequestMethod.DELETE)
    public Message deletePubSub(Integer pubId,HttpSession session){
        Integer subId= session.getAttribute("id")==null ? 2: (Integer) session.getAttribute("id");
        return pubSubService.unsubscribe(pubId,subId);
    }
}
