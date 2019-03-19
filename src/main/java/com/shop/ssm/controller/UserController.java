package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.User;
import com.shop.ssm.pojo.UserVo;
import com.shop.ssm.service.PubSubService;
import com.shop.ssm.service.UserService;
import com.shop.ssm.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    private PubSubService pubSubService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Message insertUser(User user){
        return userService.insert(user);
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
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

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public Message update(User user){
        return userService.update(user);
    }

    @RequestMapping(value  = "/user/{id}",method = RequestMethod.DELETE)
    public Message delete(@PathVariable(value = "id") Integer id){
        return userService.delete(id);
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Message getUsers(UserVo userVo,int page,int rows){
        return userService.getUsers(userVo,page,rows);
    }

    /**
     * 查找粉丝
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "usersByPubId",method = RequestMethod.GET)
    public Message getUsersByPubId(HttpSession session,int page,int rows){
        Integer pubId= (Integer) session.getAttribute("id")==null ? 2 : (Integer) session.getAttribute("id");
        return userService.getSubsByPub(pubId, page, rows);
    }

}
