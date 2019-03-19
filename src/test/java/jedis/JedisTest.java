package jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by Administrator on 2019/3/14.
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis ("192.168.0.107",6379);
        Set a=jedis.keys("postList*");
        if(a.size()==0){
            System.out.println("no result");
        }
    }

}
