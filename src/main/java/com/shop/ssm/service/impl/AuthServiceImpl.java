package com.shop.ssm.service.impl;

import com.shop.ssm.aop.PrivlegeInfo;
import com.shop.ssm.mapper.AuthorityMapper;
import com.shop.ssm.pojo.Authority;
import com.shop.ssm.pojo.Function;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/1/29.
 */
@Service
public class AuthServiceImpl implements AuthService {
    public void insertAuth(Integer userId, List<Integer> functionIds) {

    }

    public List<Function> getFunctionsByUserId(String userId) {
        return null;
    }
//    @Autowired
//    public AuthorityMapper authorityMapper;
//
//    public void insertAuth(Integer userId, List<Integer> functionIds) {
//        authorityMapper.insert(userId,functionIds);
//    }
//
//    @PrivlegeInfo(name="getFunctions")
//    public List<Function> getFunctionsByUserId(String userId){
//        return authorityMapper.getFunctionsByUserId(userId);
//    }
}
