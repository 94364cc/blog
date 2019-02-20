package jedis.MQ;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2018/10/30.
 */
public class RedisConsumer {

    public static void main(String[] args){

        ScheduleMQ mq = new ScheduleMQ();
        mq.start();
    }

}
