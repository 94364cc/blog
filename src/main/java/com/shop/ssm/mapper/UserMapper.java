package com.shop.ssm.mapper;

import com.shop.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectByUserName(@Param("userName")String userName);
    User selectByPrimaryKey(@Param("id") Integer id);
    void insert(User user);
    User selectOneByExample(User user);
    void updateByExampleSelective(User user);
    List<User> selectUsers(User user);
    List<User> selectSubsByPub(@Param("pubId")Integer pubId);
    List<User> selectUsersByIds(@Param("ids")List<Integer> ids);

}