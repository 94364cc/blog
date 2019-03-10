package com.shop.ssm.service;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.User;
import com.shop.ssm.pojo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface UserService {
    public Message insert(User user);
    public Message login(String username,String pwd);
    public Message update(User user);
    public Message delete(Integer userId);
    public Message getUsers(UserVo userVo,int page,int size);
    public List<User> getSubsByPub(Integer pubId);
}
