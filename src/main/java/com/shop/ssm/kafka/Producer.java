package com.shop.ssm.kafka;

import com.shop.ssm.controller.BusinessRuntimeException;
import com.shop.ssm.pojo.ResultCode;
import com.shop.ssm.utils.Util;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/**
 * Created by Administrator on 2019/3/7.
 */
public class Producer implements Runnable{
    @Value("${BROKER_LIST}")
    private static String BROKER_LIST;

    private String topic;
    private String message;
    private KafkaProducer<String,String> producer;

    public static Properties initProperties(){
        Properties config=new Properties();
        config.setProperty("bootstrap.servers",BROKER_LIST);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return config;
    }
    public Producer(String topic,String message){
        this.topic=topic;
        this.message=message;
        producer=new KafkaProducer<String, String>(initProperties());
    }
    public void run() {
        ProducerRecord<String,String> record=null;
        record=new ProducerRecord<String, String>(topic,message);
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(Util.isNotNull(e)){
                    throw new BusinessRuntimeException(ResultCode.KAFKA_PROCDUCE_WRONG);
                }
            }
        });
        producer.close();
    }
}
