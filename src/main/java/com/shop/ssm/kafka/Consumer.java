package com.shop.ssm.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContextEvent;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Administrator on 2019/3/7.
 */
public class Consumer implements Runnable{
    private KafkaConsumer<String,String> consumer=null;

    private String topic;
    public static Properties initProperties(){
        Properties properties = new Properties();
        properties.setProperty("group.id","test3");
        //自动提交
        properties.put("enable.auto.commit",true);
        //自动提交间隔时间
        properties.put("auto.commit.interval.ms",3000);
        //从头开始消费
//        properties.put("auto.offset.reset","earliest");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.107:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
    public Consumer(String topic,ServletContextEvent event){
        this.topic=topic;
        consumer=new KafkaConsumer<String, String>(initProperties());
    }
    public void run() {

    }
}
