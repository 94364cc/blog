package com.shop.ssm.mapper;


import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;
import org.apache.ibatis.annotations.Param;

public interface PostMapper {

    void insert(Post post);
}