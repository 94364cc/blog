package kafka;

import com.shop.ssm.pojo.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Administrator on 2019/3/2.
 */
public class KafkaProducers {
    //设置实例产生消息的总数
    private static final  int MSG_NUM=10;
    private static final String TOPIC="javaTest";
    private static final String BROKER_LIST="192.168.0.107:9092";
    private static KafkaProducer<String,String> producer=null;
    static{
        Properties configs=initConfig();
        producer=new KafkaProducer<String,String>(configs);
    }
    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }

    public static void produce(){
        ProducerRecord<String,String> record=null;
        Record message=new Record(2,new ArrayList<Integer>(){{add(2);}},"I send a new post");
        try {
            for (int i = 0; i <MSG_NUM ; i++) {
                record=new ProducerRecord<String, String>(TOPIC,null,message.getProducerId().toString(),message.getConsumerIds().toString());
                producer.send(record);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }

    @Test
    public void Test01() {
        produce();
    }
}
