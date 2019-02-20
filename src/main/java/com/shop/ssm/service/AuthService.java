package com.shop.ssm.service;

import com.shop.ssm.pojo.Authority;
import com.shop.ssm.pojo.Function;
import com.shop.ssm.pojo.Message;

import java.util.List;

/**
 * Created by Administrator on 2019/1/29.
 */
public interface AuthService {

    public void insertAuth(Integer userId,List<Integer> functionIds);
    public List<Function> getFunctionsByUserId(String userId);
}
