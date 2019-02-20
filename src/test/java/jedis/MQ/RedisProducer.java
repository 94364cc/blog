package jedis.MQ;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2018/10/30.
 */
public class RedisProducer {
    public static void main(String[] args){

        Jedis jedis = new Jedis("192.168.2.103", 6379);
        for(int i = 0;i<10;i++) {
            jedis.lpush("informList","value_" + i);
        }
        jedis.close();
    }
}
