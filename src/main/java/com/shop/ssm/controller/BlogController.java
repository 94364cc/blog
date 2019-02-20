package com.shop.ssm.controller;

import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;
import com.shop.ssm.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/10/21.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

//    @Autowired
//    public BlogService blogService;
//
//    @ResponseBody
//    @RequestMapping("/viewCount/{postId}")
//    public void ViewCount(String postId){
//        blogService.ViewCount(postId);
//    }
//
//    @ResponseBody
//    @RequestMapping("/add")
//    public Message ViewCount(Post post){
//        //序列化存储对象
//        /*return blogService.addPost(post);*/
//        //hash存储对象
//        return blogService.addHashPost(post);
//    }
//
//    @ResponseBody
//    @RequestMapping("/queryList")
//    public List<Post> queryPostList(Integer pageSize,Integer page){
//        return blogService.queryPostList(pageSize,page);
//    }
//
//    @ResponseBody
//    @RequestMapping("/addEmail")
//         public Message addEmail(String email){
//        return blogService.addEmail(email);
//    }
//
//    @ResponseBody
//    @RequestMapping("/subscribe")
//    public Message subscribe(String writerId,String readId){
//        return blogService.subscribe(writerId,readId);
//    }
}
