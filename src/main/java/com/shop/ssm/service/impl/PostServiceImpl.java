package com.shop.ssm.service.impl;

import com.shop.ssm.mapper.PostMapper;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.PostService;
import com.shop.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/10/21.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    public PostMapper postMapper;
    @Autowired
    public UserService userService;

    @Transactional(propagation= Propagation.REQUIRED , isolation = Isolation.DEFAULT)
    public Message insert(Post post,Integer pubId) {
        postMapper.insert(post);
        List<User> subIds= userService.getSubsByPub(pubId);
        return Message.Ok();
    }

//    @Autowired
//    public JedisClient jedisClient;
//    @Autowired
//    public RedisMsgPubSubListener listener;

//
//    String POST_PREFIEW="post:";
//    String TAG_PREFIEW="tag:";
//    String POST_LATER=":post";
//    String POST_LIST="post:list";
//    String POST_SORT_LIST="post:sort:list";
//    String POST_EMAIL="post:email";
//    String SUBSCRIBE="subscribe:";
//    //存储文章的访问量
//    public void ViewCount(String postId) {
//        String count;
//        if(jedisClient.get(POST_PREFIEW + postId)==null){
//            count="0";
//        }else{
//            count=jedisClient.get(POST_PREFIEW + postId);
//        }
//        jedisClient.incr(POST_PREFIEW + postId);
//    }
//
//    //发表帖子
//    public Message addPost(Post post) {
//        postMapper.insert(post);
//        //不等于null说明存储成功,将增加的结果写入redis
//        if(post.getId()!=null){
//            byte[] bytes=utils.serialize(post);
//            jedisClient.setObject(post.getId()+"",bytes);
//            //有序存储postId,方便顺序查找
//            jedisClient.lpush(POST_LIST, post.getId() + "");
//            //根据访问量有序查找列表(第二个参数是权重，每访问一次+1)
//            jedisClient.zadd(POST_SORT_LIST,0,post.getId()+"");
//
//        }
//        return Message.ok();
//    }
//
//    //采用hash来存储post对象(修改)
//    //好处:修改或查询某个属性不用反序列化，然后再取，占用资源少
//    public Message addHashPost(Post post) {
//        postMapper.insert(post);
//        //不等于null说明存储成功,将增加的结果写入redis
//        if(post.getId()!=null){
//            Integer postId=post.getId();
//            jedisClient.hset(POST_PREFIEW+postId,"id",postId+"");
//            jedisClient.hset(POST_PREFIEW+postId,"userId",post.getUserId()+"");
//            jedisClient.hset(POST_PREFIEW+postId,"title",post.getTitle());
//            jedisClient.hset(POST_PREFIEW+postId,"block",post.getBlock());
//            jedisClient.hset(POST_PREFIEW+postId,"content",post.getContent());
//            //有序存储postId,方便顺序查找
//            jedisClient.lpush(POST_LIST, postId + "");
//            //实现根据block查找想关的post
//            String block=post.getBlock();
//            List<String> blocks= Arrays.asList(block.split(","));
//            for (String str:blocks){
//                jedisClient.sadd(TAG_PREFIEW+str+POST_LATER,postId+"");
//            }
//            //发布订阅模式
////            Set<String> subKey = jedisClient.keys(SUBSCRIBE + post.getUserId());
////            if(!subKey.isEmpty()){
////                Iterator iterator=subKey.iterator();
////                while(iterator.hasNext()){
////                    String readId= (String) iterator.next();
////                    //1.订阅
////                    jedisClient.subscribe(listener,SUBSCRIBE + post.getUserId());
////                    //2.发布
////                    //只传送一个title
////                    jedisClient.publish(SUBSCRIBE + post.getUserId(),post.getTitle());
////                    //3.退订
////                    listener.unsubscribe();
////                }
////            }
//    }
//    return Message.ok();
//    }
//
//    /*
//    单挑访问post
//    * */
//    public Post queryPost(String postId) {
//        Integer id=Integer.parseInt(jedisClient.hget(POST_PREFIEW + postId,"id"));
//        Integer userId=Integer.parseInt(jedisClient.hget(POST_PREFIEW + postId, "userId"));
//        String title=jedisClient.hget(POST_PREFIEW+postId,"title");
//        String block=jedisClient.hget(POST_PREFIEW+postId,"block");
//        String content=jedisClient.hget(POST_PREFIEW+postId,"content");
//
//        //每次查一次，访问加1
//        jedisClient.zincrby(POST_SORT_LIST,new Double(1),postId);
//        return new Post(id,userId,title,block,content);
//    }
//
//    /*
//    有序查找列表（可以用sorted set 来代替，这样可以实现用时间排序，查找某个月的post）
//    * */
//    public List<Post> queryPostList(Integer pageSize,Integer page) {
//        //计算start end
//        long start =(page-1)*pageSize;
//        long end = page*pageSize-1;
//        List<String> postIds= jedisClient.lrange(POST_LIST,start,end);
//        List<Post> result=new ArrayList<Post>();
//        for(String postId:postIds){
//            result.add(queryPost(postId));
//        }
//        return result;
//    }
//
//    //添加邮箱到集合中，待会去发邮件
//    public Message addEmail(String email){
//        //忽略邮箱验证
//        jedisClient.lpush(POST_EMAIL,email);
//        return Message.ok();
//    }
//
//    //发送邮件
//    public void sendEmail() {
//        List<String> emails= jedisClient.brpop(POST_EMAIL,0);
//        for(String email:emails){
//            System.out.println(email);
//        }
//    }
//
//    //订阅
//    public Message subscribe(String writerId,String readId){
//        //将订阅关系存储在set中
//        jedisClient.sadd(SUBSCRIBE+writerId,readId);
//        return Message.ok();
//    }



}
