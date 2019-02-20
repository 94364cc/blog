package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping(value = "user",method = RequestMethod.POST)
    @ResponseBody
    public Message regist(User user){
        return userService.insertUser(user);
    }


    @RequestMapping(value = "user",method = RequestMethod.GET)
    @ResponseBody
    public Message login(String userName,String password,HttpSession httpSession){
        Message message= userService.login(userName,password);
        User user= (User) message.getData();
        if(message.getStatus().equals("1")){
            httpSession.setAttribute("nick",user.getNick());
            //放到redis里面去存，暂时先这么写
            httpSession.setAttribute("status","1");
        }
        message.setData(null);
        return message;
    }

    @RequestMapping(value = "user",method = RequestMethod.PUT)
    @ResponseBody
    public Message update(User user){
        return userService.updateUserInfo(user);
    }
}
