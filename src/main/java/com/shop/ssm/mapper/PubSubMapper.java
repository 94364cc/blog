package com.shop.ssm.mapper;

import com.shop.ssm.pojo.PubSub;
import com.shop.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubSubMapper {
    void subscribe(@Param("pubId")Integer pubId,@Param("subId")Integer subId);
    void unsubscribe(@Param("pubId")Integer pubId,@Param("subId")Integer subId);
    List<Integer> getSubsByPubId(@Param("pubId") Integer pubId);

}