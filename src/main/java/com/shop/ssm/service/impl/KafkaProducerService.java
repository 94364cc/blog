package com.shop.ssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shop.ssm.controller.BusinessRuntimeException;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.ResultCode;
import com.shop.ssm.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2019/3/10.
 */
@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * kafka发送消息模板
     *
     * @param topic
     *            主题
     * @param value
     *            messageValue
     * @param isPartition
     *            是否使用分区 0是\1不是
     * @param partitionNum
     *            分区数 如果是使用分区为0,分区数必须大于0
    */
    public Message sndMesForTemplate(String topic, Object value, boolean isPartition, Integer partitionNum
                                                 ) {
        String key = String.valueOf(value.hashCode());
        String valueString = JSONObject.toJSONString(value);
        if (isPartition) {
            // 表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, Object>> result = kafkaTemplate.send(topic, partitionIndex, key,
                    valueString);
            return checkProRecord(result);
        } else {
            ListenableFuture<SendResult<String, Object>> result = kafkaTemplate.send(topic, key, valueString);
            return checkProRecord(result);
        }
    }

    /**
     * 根据key值获取分区索引
     *
     * @param key
     * @param partitionNum
     * @return
     */
    private int getPartitionIndex(String key, int partitionNum) {
        if (key == null) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        } else {
            int result = Math.abs(key.hashCode()) % partitionNum;
            return result;
        }
    }

    /**
     * 检查发送返回结果record
     *
     * @param res
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Message checkProRecord(ListenableFuture<SendResult<String, Object>> res) {
        Map<String, Object> m = new HashMap<String, Object>();
        if (res != null) {
            try {
                SendResult r = res.get();// 检查result结果集
                // 检查recordMetadata的offset数据，不检查producerRecord
                Long offsetIndex = r.getRecordMetadata().offset();
                if (offsetIndex != null && offsetIndex >= 0) {
                    return Message.Ok();
                } else {
                    return Message.Ok(ResultCode.KAFKA_NOT_OFFSET);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Message.Ok(ResultCode.KAFKA_SEND_TIMEOUT);
            } catch (ExecutionException e) {
                e.printStackTrace();
                return Message.Ok(ResultCode.KAFKA_SEND_TIMEOUT);
            }
        } else {
            return Message.Ok(ResultCode.KAFKA_NOT_RESULT);
        }
    }
}
