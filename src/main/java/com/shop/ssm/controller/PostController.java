package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;
import com.shop.ssm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;

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
//        Integer pubId= (Integer) session.getAttribute("userId");
        Integer pubId=2;
        return postService.insert(post,pubId);
    }
//    @ResponseBody
//    @RequestMapping("/viewCount/{postId}")
//    public void ViewCount(String postId){
//        postService.ViewCount(postId);
//    }
//
//    @ResponseBody
//    @RequestMapping("/add")
//    public Message ViewCount(Post post){
//        //序列化存储对象
//        /*return postService.addPost(post);*/
//        //hash存储对象
//        return postService.addHashPost(post);
//    }
//
//    @ResponseBody
//    @RequestMapping("/queryList")
//    public List<Post> queryPostList(Integer pageSize,Integer page){
//        return postService.queryPostList(pageSize,page);
//    }
//
//    @ResponseBody
//    @RequestMapping("/addEmail")
//         public Message addEmail(String email){
//        return postService.addEmail(email);
//    }
//
//    @ResponseBody
//    @RequestMapping("/subscribe")
//    public Message subscribe(String writerId,String readId){
//        return postService.subscribe(writerId,readId);
//    }
}
