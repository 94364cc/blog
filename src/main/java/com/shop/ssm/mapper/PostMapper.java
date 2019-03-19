package com.shop.ssm.mapper;


import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {

    void insert(Post post);
    List<Post> getPosts(Post post);
    Post getPostByKey(@Param("id")Integer id);
}