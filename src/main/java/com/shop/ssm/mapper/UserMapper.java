package com.shop.ssm.mapper;

import com.shop.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByUserName(@Param("userName")String userName);
    void insert(User user);
    User selectOneByExample(User user);
}