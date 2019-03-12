package com.shop.ssm.kafka;

import com.alibaba.fastjson.JSONObject;
import com.shop.ssm.service.impl.KafkaConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/3/10.
 */
public class KafkaConsumerServer implements MessageListener<String, Object>{

    protected final Logger LOGGER = LoggerFactory.getLogger("KafkaConsumerServer");

    @Resource
    public KafkaConsumerService consumerService;

    /**
     * 监听器自动执行该方法 消费消息 自动提交offset 执行业务代码 （high level api
     * 不提供offset管理，不能指定offset进行消费）
     */
    public void onMessage(ConsumerRecord<String, Object> record) {
        String topic = record.topic();
        String value = (String) record.value();
        LOGGER.info("=============kafka监听消息开始=============topic：" + topic + "=======value" + value);
        try {
            consumerService.remindSuber(value);
        } catch (Exception ex) {
            LOGGER.error("***********KAFKA消息监听异常" + ex.getMessage() + "***********", ex);
        }
        LOGGER.info("=============kafka监听消息开结束=============topic：" + topic + "=======value" + value);
    }
}
