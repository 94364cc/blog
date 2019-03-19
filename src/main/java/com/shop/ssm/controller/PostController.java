package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;
import com.shop.ssm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import java.util.List;

/**
 * Created by Administrator on 2018/10/21.
 */
@Controller
public class PostController {

    @Autowired
    public PostService postService;

    @RequestMapping(value = "/post" , method = RequestMethod.POST)
    @ResponseBody
    public Message insertPost(Post post,HttpSession session){
        Integer pubId= (Integer) session.getAttribute("userId")==null ? 2 : (Integer) session.getAttribute("userId");
        return postService.insert(post,pubId);
    }


    @RequestMapping(value = "/post/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public Message getPost(@PathVariable("id") Integer id){
        Post post= postService.getPost(id);
        return Message.Ok(post);
    }
//    @ResponseBody
//    @RequestMapping("/viewCount/{postId}")
//    public void ViewCount(String postId){
//        postService.ViewCount(postId);
//    }
//
    @ResponseBody
    @RequestMapping("/add")
    public Message ViewCount(Post post){
        //序列化存储对象
        /*return postService.addPost(post);*/
        //hash存储对象
        return postService.addHashPost(post);
    }

    @ResponseBody
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public Message getPosts(Post post,Integer page,Integer pageSize){
        return postService.getPosts(post,pageSize,page);
    }

}
