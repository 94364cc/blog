package com.shop.ssm.service;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;

import java.util.List;

/**
 * Created by Administrator on 2018/10/21.
 */
public interface PostService {
//    public void ViewCount(String postId);
    public Message insert(Post post,Integer pubId);
    public Message addHashPost(Post post);
    public Post getPost(Integer postId);
    public Message getPosts(Post post,Integer pageSize,Integer page);
//    public Message addEmail(String email);
//    public void sendEmail();
}
