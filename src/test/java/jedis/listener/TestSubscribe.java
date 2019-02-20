package jedis.listener;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2018/11/5.
 */
public class TestSubscribe {
    @Test
    public void testSubscribe() throws Exception{
        Jedis jedis = new Jedis("192.168.2.103");
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        jedis.subscribe(listener, "redisChatTest");
        //other code
    }
}
