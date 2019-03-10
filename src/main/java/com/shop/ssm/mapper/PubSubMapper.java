package com.shop.ssm.mapper;

import com.shop.ssm.pojo.PubSub;
import com.shop.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubSubMapper {
    void subscribe(PubSub pubSub);
    List<User> getSubsByPub(@Param("pubId")Integer pubId);
}