package jedis;

import com.shop.ssm.dao.JedisClient;
import com.shop.ssm.dao.impl.JedisClientSingle;
import com.shop.ssm.mapper.PostMapper;
import com.shop.ssm.pojo.Post;
import com.shop.ssm.service.BlogService;
import com.shop.ssm.service.impl.BlogServiceImpl;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/10/21.
 */
public class JedisTest {

    @Injectable
    PostMapper postMapper;

    @Test
    public void testJedis(){
        JedisClientSingle jedisClientSingle=new JedisClientSingle();
        jedisClientSingle.set("key1","val1");
        String val=jedisClientSingle.get("key1");
        System.out.println(val);
    }

    @Test
    public void testViewCount(){
        Jedis jedis = new Jedis("192.168.0.107");
        Set result=jedis.keys("post*");
        System.out.println(result.size());
    }


}
