package com.shop.ssm.service;

import com.shop.ssm.aop.PrivlegeInfo;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface UserService {
    //注册
    public Message insertUser(User user);
    //登录
    public Message login(String username,String pwd);
    public Message updateUserInfo(User user);
}
