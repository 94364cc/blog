package com.shop.ssm.service.impl;

import com.shop.ssm.aop.PrivlegeInfo;
import com.shop.ssm.mapper.UserMapper;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.UserService;
import com.shop.ssm.utils.Constant;
import com.shop.ssm.utils.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Zhangxq on 2016/7/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    //注册
    public Message insert(User user) {
        Message msg = new Message();
        //判断用户名是否可用
        User isNull=userMapper.selectByUserName(user.getUserName());
        if(isNull==null){
            //校验密码
            if (Util.match(user.getPassword())){
                user.setCreateTime(new Date());
                user.setModifyTime(new Date());
                userMapper.insert(user);
                msg.setStatus(Constant.SUCCESS);
                msg.setMessage("regist success");
            }else{
                msg.setStatus(Constant.FALSE);
                msg.setMessage("password must include word and number!");
            }
        }else{
            msg.setStatus(Constant.FALSE);
            msg.setMessage("username is already exist!");
        }
        return msg;
    }

    public Message login(String username,String pwd) {
        Message msg=new Message();
        //判断用户名密码为空
        if(!Util.isNotNull(username,pwd)){
            msg.setStatus("400");
            msg.setMessage("username or password is not available");
        }
        //查找用户是否存在
        User user=userMapper.selectByUserName(username);
        if(user!=null){
            //校验用户名密码
            User loginUser = new User();
            loginUser.setUserName(username);
            loginUser.setPassword(user.getPassword());
            User isExist=userMapper.selectOneByExample(loginUser);
            if(isExist!=null){
                msg.setStatus(Constant.SUCCESS);
                msg.setMessage(username+ " welcome!");
                msg.setData(isExist);
            }else{
                msg.setStatus(Constant.FALSE);
                msg.setMessage("password or username is not available");
            }
        }else{
            msg.setStatus(Constant.FALSE);
            msg.setMessage("username or password is not available!");
        }
        return msg;
    }

    public Message update(User user) {
        user.setModifyTime(new Date());
        userMapper.updateByExampleSelective(user);
        return Message.Ok();
    }

    public Message delete(Integer userId) {
        User user =new User();
        user.setId(userId);
        user.setIsDelete(Constant.IS_EXIST);
        user.setModifyTime(new Date());
        userMapper.updateByExampleSelective(user);
        return Message.Ok();
    }
}
