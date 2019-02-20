package jedis.listener;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2018/11/5.
 */
public class TestPublish {
    @Test
    public void testPublish() throws Exception{
        Jedis jedis = new Jedis("192.168.2.103");
        jedis.publish("redisChatTest", "part 1");
//        Thread.sleep(1000);
//        jedis.publish("redisChatTest", "part 2");
//        Thread.sleep(1000);
//        jedis.publish("redisChatTest", "part 3");
    }
}