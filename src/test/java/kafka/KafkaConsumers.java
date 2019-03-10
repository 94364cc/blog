package kafka;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import kafka.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * Created by Administrator on 2019/3/2.
 */
public class KafkaConsumers {
    private static final String TOPIC="javaTest";
    private static final String BROKER_LIST="192.168.0.107:9092";
    private static KafkaConsumer<String,String> consumer=null;

    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.setProperty("group.id","test3");
        properties.setProperty("client.id","test");
        properties.put("enable.auto.commit",true);
        properties.put("auto.commit.interval.ms",3000);
        properties.put("auto.offset.reset","earliest");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
    static {
        Properties configs=initConfig();
        consumer=new KafkaConsumer<String, String>(configs);
    }

    public static void consume(final String topic){
        //订阅
        consumer.subscribe(new ArrayList<String>(){{add(topic);}});
        final TopicPartition partition=new TopicPartition(topic,0);
        ConsumerRecords<String,String> records=null;
        try{
            while (true){
                System.out.println("poll start");
                records=consumer.poll(1000);
                for (ConsumerRecord<String,String> record:records){
                    System.out.printf("partition =%d,offset=%d key=%s value=%s%n",record.partition(),record.offset(),record.key(),record.value());
                }
            }
        }catch (Exception e){

        }finally {
            consumer.close();
        }
    }

    @Test
    public void consume(){
        consume(TOPIC);
    }
}
