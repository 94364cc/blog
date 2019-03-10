package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/1/29.
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController {

//    @Autowired
//    public AuthService authService;
//
//    @ResponseBody
//    @RequestMapping(value="/add",method = RequestMethod.POST)
//    public Message insertUserAuth(Integer userId,Integer[] functionIds){
//        authService.insertAuth(userId, Arrays.asList(functionIds));
//        return new Message(Constant.SUCCESS,"success",null);
//    }
//
//    //查询某个用户的所有权限
//    @ResponseBody
//    @RequestMapping(value= "/getByUserId", method=RequestMethod.GET)
//    public Message getByUserId(String userId){
//        try {
//            throw  new CustomException(400,"sss");
//        } catch (CustomException e) {
//            e.printStackTrace();
//        }
//        List<Function> result=authService.getFunctionsByUserId(userId);
//        return new Message(Constant.SUCCESS,null,result);
//    }
}
