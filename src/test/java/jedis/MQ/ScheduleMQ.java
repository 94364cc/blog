package jedis.MQ;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Administrator on 2018/10/30.
 */
public class ScheduleMQ extends Thread {
    @Override
    public void run() {
        while(true) {
            Jedis jedis = new Jedis("192.168.2.103", 6379);
            //阻塞式brpop，List中无数据时阻塞
            //参数0表示一直阻塞下去，直到List出现数据
            List<String> list = jedis.brpop(0, "informList");
            for(String s : list) {
                System.out.println(s);
            }
            jedis.close();

        }
    }

}
