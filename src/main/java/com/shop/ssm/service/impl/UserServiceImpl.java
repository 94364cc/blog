package com.shop.ssm.service.impl;

import com.shop.ssm.aop.PrivlegeInfo;
import com.shop.ssm.mapper.UserMapper;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.UserService;
import com.shop.ssm.utils.Constant;
import com.shop.ssm.utils.Md5;
import com.shop.ssm.utils.Util;
import org.springframework.stereotype.Service;
import scala.util.parsing.combinator.testing.Str;

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
    public Message insertUser(User user) {
        Message msg = new Message();
//        //判断用户名是否可用
        User isNull=userMapper.selectByUserName(user.getUserName());
        if(isNull==null){
            //校验密码
            if (Util.match(user.getPassword())){
                //获取严
                String salt= Util.getSalt();
                //将严和密码进行md5加密
                String userPwd= Md5.GetMD5Code(user.getPassword() + salt);
                user.setSalt(salt);
                user.setUserName(user.getUserName());
                user.setPassword(userPwd);
                user.setCreateTime(new Date());
                user.setIsDelete(Constant.IS_EXIST);
                userMapper.insert(user);
                msg.setStatus("1");
                msg.setMessage("regist success");
            }else{
                msg.setStatus("2");
                msg.setMessage("password must include word and number!");
            }
        }else{
            msg.setStatus("2");
            msg.setMessage("username is already exist!");
        }
        return msg;
    }

    public Message login(String username,String pwd) {
        Message msg=new Message();
        //查找用户是否存在
        User user=userMapper.selectByUserName(username);
        if(user!=null){
            String salt=user.getSalt();
            String userPwd= Md5.GetMD5Code(pwd + salt);
            //校验用户名密码
            User loginUser = new User();
            loginUser.setUserName(username);
            loginUser.setPassword(userPwd);
            User isExist=userMapper.selectOneByExample(loginUser);
            if(isExist!=null){
                msg.setStatus("1");
                msg.setMessage(username+ " welcome!");
                msg.setData(isExist);
            }else{
                msg.setStatus("2");
                msg.setMessage("password or username is not available");
            }
        }else{
            msg.setStatus("2");
            msg.setMessage("username or password is not available!");
        }
        return msg;
    }

    public Message updateUserInfo(User user) {
        return null;
    }
}
