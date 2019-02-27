package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.UserService;
import com.shop.ssm.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping(value = "user",method = RequestMethod.POST)
    @ResponseBody
    public Message regist(User user){
        return userService.insert(user);
    }


    @RequestMapping(value = "login",method = RequestMethod.GET)
    @ResponseBody
    public Message login(String userName,String password,HttpSession httpSession){
        Message message= userService.login(userName,password);
        User user= (User) message.getData();
        if(message.getStatus().equals(Constant.SUCCESS)){
            httpSession.setAttribute("nick",user.getNick());
            httpSession.setAttribute("userId",user.getId());
        }
        message.setData(null);
        return message;
    }

    @RequestMapping(value = "user",method = RequestMethod.PUT)
    @ResponseBody
    public Message update(User user){
        return userService.update(user);
    }

    @RequestMapping(path  = "/user/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Message delete(@PathVariable(value = "id") Integer id){
        return userService.delete(id);
    }
}
