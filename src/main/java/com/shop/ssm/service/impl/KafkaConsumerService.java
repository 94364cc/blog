package com.shop.ssm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.UserService;
import com.shop.ssm.utils.EmailCilent;
import org.apache.ibatis.type.TypeReference;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/3/11.
 */
@Service
public class KafkaConsumerService {

    @Resource
    public UserService userService;

    public void remindSuber(String value){
        EmailCilent emailCilent=EmailCilent.getInstance();
        value=value.substring(value.indexOf("[")+1,value.indexOf("]"));
        // 获取subIds
        List subIds =  strToList(value);
        //批量根据userId查询获取email
        List<User> subers=userService.getUsersByIds(subIds);
        List<String> emails=subers.stream().map(User::getEmail).collect(Collectors.toList());
        for (String email:emails){
            emailCilent.sendEmial("project email team",email,"remind","remind");
        }
    }

    public static List strToList(String str){
        return Arrays.asList(str.split(","));
    }

}
