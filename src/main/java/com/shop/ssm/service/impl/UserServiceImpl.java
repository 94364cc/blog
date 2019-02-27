package com.shop.ssm.service.impl;

import com.shop.ssm.controller.BusinessRuntimeException;
import com.shop.ssm.mapper.UserMapper;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.ResultCode;
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
        if(isNull!=null) throw new BusinessRuntimeException(ResultCode.USERNAME_ALREADY_EXISTS);
        //校验密码
        if (!Util.match(user.getPassword())) throw new BusinessRuntimeException(ResultCode.PASSWORD_INVAILD);
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        userMapper.insert(user);
        msg.setStatus(Constant.SUCCESS);
        msg.setMessage("regist success");
        return msg;
    }

    public Message login(String username,String pwd) {
        Message msg=new Message();
        //判断用户名密码为空
        if(!Util.isNotNull(username,pwd))
            throw new BusinessRuntimeException(ResultCode.USERNAME_OR_PASSWORD_EMPTY);
        //查找用户是否存在
        User user=userMapper.selectByUserName(username);
        if(user==null)
            throw new BusinessRuntimeException(ResultCode.USERNAME_OR_PASSWORD_EMPTY);
        //校验用户名密码
        User loginUser = new User();
        loginUser.setUserName(username);
        loginUser.setPassword(pwd);
        User isExist=userMapper.selectOneByExample(loginUser);
        if(isExist!=null){
            msg.setStatus(Constant.SUCCESS);
            msg.setMessage(username+ " welcome!");
            msg.setData(isExist);
        }else{
            throw new BusinessRuntimeException(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        return msg;
    }

    public Message update(User user) {
        User loginUser=userMapper.selectByPrimaryKey(user.getId());
        if(loginUser==null) throw new BusinessRuntimeException(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        if(Util.isNotNull(user.getPassword())){
            if(!Util.match(user.getPassword())) throw new BusinessRuntimeException(ResultCode.PASSWORD_INVAILD);
        }
        user.setModifyTime(new Date());
        userMapper.updateByExampleSelective(user);
        return Message.Ok();
    }

    public Message delete(Integer userId) {
        User user =new User();
        user.setId(userId);
        user.setIsDelete(Constant.IS_DELETE);
        user.setModifyTime(new Date());
        userMapper.updateByExampleSelective(user);
        return Message.Ok();
    }
}
