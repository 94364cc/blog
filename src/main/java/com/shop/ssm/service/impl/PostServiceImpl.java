package com.shop.ssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shop.ssm.dao.JedisClient;
import com.shop.ssm.mapper.PostMapper;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.Post;
import com.shop.ssm.pojo.PubSub;
import com.shop.ssm.pojo.User;
import com.shop.ssm.service.PostService;
import com.shop.ssm.service.PubSubService;
import com.shop.ssm.service.UserService;
import com.shop.ssm.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/10/21.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    public PostMapper postMapper;
    @Autowired
    public UserService userService;
    @Autowired
    public PubSubService pubSubService;

    @Autowired
    public KafkaProducerService producerService;


    @Transactional(propagation= Propagation.REQUIRED , isolation = Isolation.DEFAULT)
    public Message insert(Post post,Integer pubId) {
        Map map = new HashMap();
        postMapper.insert(post);
        //给订阅的用户发送提醒
        List<Integer> subIds= pubSubService.getSubsByPubId(pubId);
        map.put(pubId, subIds);
        String message = JSONObject.toJSON(map).toString();
        //提醒发布
        return producerService.sndMesForTemplate(Constant.TOPIC,message,false,null);
    }

    @Autowired
    public JedisClient jedisClient;
//    @Autowired
//    public RedisMsgPubSubListener listener;
    String POST_PREFIEW="post:";
    String TAG_PREFIEW="tag:";
    String POST_LATER=":post";
    String POST_LIST="postList";
    String POST_SORT_LIST="post:sort:list";
    String POST_EMAIL="post:email";
    int EXPIRE_SEC=3600;
    String ID="id";
    String USERID="userId";
    String TITLE="title";
    String LABEL="label";
    String CONTENT="content";
    String CREATETIME="createTime";
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
    //采用hash来存储post对象(修改)
    //好处:修改或查询某个属性不用反序列化，然后再取，占用资源少
    public Message addHashPost(Post post) {
        postMapper.insert(post);
        //不等于null说明存储成功,将增加的结果写入redis
        if (post.getId() != null) {
            Integer postId = post.getId();
            jedisClient.hset(POST_PREFIEW + postId, "id", postId + "");
            jedisClient.hset(POST_PREFIEW + postId, "userId", post.getUserId() + "");
            jedisClient.hset(POST_PREFIEW + postId, "title", post.getTitle());
            jedisClient.hset(POST_PREFIEW + postId, "block", post.getLabel());
            jedisClient.hset(POST_PREFIEW + postId, "content", post.getContent());
            //有序存储postId,方便顺序查找
            jedisClient.lpush(POST_LIST, postId + "");
            //实现根据block查找想关的post
            String block = post.getLabel();
            List<String> blocks = Arrays.asList(block.split(","));
            for (String str : blocks) {
                jedisClient.sadd(TAG_PREFIEW + str + POST_LATER, postId + "");
            }
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
        }
        return Message.Ok();
    }

    /*
    单条访问post
    * */
    public Post getPost(Integer postId) {
        Post post=null;
        //查询缓存是否存在
        String id=jedisClient.hget(POST_PREFIEW + postId, "id");
        if(id!=null){
            post=redisGet(postId);
            return post;
        }
        post=postMapper.getPostByKey(postId);
        //填充缓存
        redisPut(post);
        return post;
        //每次查一次，访问加1
//        jedisClient.zincrby(POST_SORT_LIST,new Double(1),postId+"");
    }

    /*
    有序查找列表（可以用sorted set 来代替，这样可以实现用时间排序，查找某个月的post）
    * */
    public Message getPosts(Post post,Integer pageSize,Integer page) {
        List<String> subIds=new ArrayList<String>();
        List<Post> result=new ArrayList<Post>();
        //首先判断是否存在缓存
        if(jedisClient.keys(POST_LIST+"*").size()==0){
            //查询所有的数据(按照时间倒序)
             List<Post> posts= postMapper.getPosts(post);
            //填充缓存
            for (int i = 0; i <posts.size() ; i++) {
                Post p=posts.get(i);
                redisPut(p);
                //将id存到list里面留备分页查询
                jedisClient.rpush(POST_LIST, p.getId() +"");
            }
        }
        long start =(page-1)*pageSize;
        long end = page*pageSize-1;
        subIds= jedisClient.lrange(POST_LIST,start,end);
        for (int i = 0; i <subIds.size() ; i++) {
            result.add(getPost(Integer.valueOf(subIds.get(i))));
        }
        return Message.Ok(result);
    }
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



    //redis 新增
    public void redisPut(Post post){
        Integer postId=post.getId();
        jedisClient.hset(POST_PREFIEW+postId,ID,postId+"");
        jedisClient.hset(POST_PREFIEW+postId,USERID,post.getUserId()+"");
        jedisClient.hset(POST_PREFIEW+postId,TITLE,post.getTitle());
        jedisClient.hset(POST_PREFIEW+postId,LABEL,post.getLabel());
        jedisClient.hset(POST_PREFIEW+postId,CONTENT,post.getContent());
        jedisClient.hset(POST_PREFIEW+postId,CREATETIME,post.getCreateTime()+"");
        //设置过期时间
        jedisClient.expire(POST_PREFIEW+postId,EXPIRE_SEC);
    }

    //redis 查询
    public Post redisGet(Integer postId){
        Integer id=Integer.parseInt(jedisClient.hget(POST_PREFIEW + postId,ID));
        Integer userId=Integer.parseInt(jedisClient.hget(POST_PREFIEW + postId, USERID));
        String title=jedisClient.hget(POST_PREFIEW+postId,TITLE);
        String label=jedisClient.hget(POST_PREFIEW+postId,LABEL);
        String content=jedisClient.hget(POST_PREFIEW+postId,CONTENT);
        String createTime=jedisClient.hget(POST_PREFIEW+postId,CREATETIME);
        //刷新过期时间
        jedisClient.expire(POST_PREFIEW+postId,EXPIRE_SEC);
        return new Post(id,userId,title,label,content,new Date());
    }

}
