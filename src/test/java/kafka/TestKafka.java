package kafka;


import com.sun.corba.se.impl.oa.toa.TOA;
import kafka.admin.AdminUtilities;
import kafka.admin.AdminUtils;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.requests.MetadataResponse.*;
import org.apache.kafka.common.security.JaasUtils;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2019/3/1.
 */
public class TestKafka {
    //连接 Zk
    private static final String ZK_CONNECT= "192.168.0.107:2181";

    private static final String TOPIC = "javaTest";
//    private static final String BROKER_LIST = "192.168.0.107:9092";
    private static KafkaProducer<String,String> producer = null;
    // sess on 过期时间
    private static final int SESSION_TIMEOUT = 30000 ;
    //连接超时时间
    private static final int CONNECT_TIMEOUT = 30000 ;
    private static Properties initConfig(){
        Properties properties = new Properties();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }


    public static void createTopic(String topic,int partition,int repilca, Properties properties) {
        ZkUtils zkUtils = null;
        try {
            //实例化 ZkUtils
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT,
                    JaasUtils.isZkSecurityEnabled());
            if (!AdminUtils.topicExists(zkUtils, topic)) {
                AdminUtils.createTopic(zkUtils, topic, partition, repilca, properties, AdminUtils.createTopic$default$6());
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    public static void modifyTopic(String topic,Properties properties){
        ZkUtils zkUtils=null;
        try{
            zkUtils = ZkUtils.apply(ZK_CONNECT , SESSION_TIMEOUT, CONNECT_TIMEOUT,
                    JaasUtils. isZkSecurityEnabled());
            Properties curProp = AdminUtils.fetchEntityConfig ( zkUtils,
                    ConfigType.Topic(),topic);// 添加新修改的配置
            curProp.putAll(properties);
            AdminUtils.changeTopicConfig(zkUtils,topic,curProp);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zkUtils.close();
        }
    }


    public static void addPartitions(String topic,Integer partitions){
        ZkUtils zkUtils=null;
        try{
            zkUtils=zkUtils.apply(ZK_CONNECT , SESSION_TIMEOUT, CONNECT_TIMEOUT,
                    JaasUtils. isZkSecurityEnabled());
            AdminUtils.addPartitions(zkUtils,topic,partitions,null,true,AdminUtils.addPartitions$default$6());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zkUtils.close();
        }
    }

    public static void delTopic(String topic){
        ZkUtils zkUtils=null;
        try{
            zkUtils=zkUtils.apply(ZK_CONNECT , SESSION_TIMEOUT, CONNECT_TIMEOUT,
                    JaasUtils. isZkSecurityEnabled());
            AdminUtils.deleteTopic(zkUtils,topic);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zkUtils.close();
        }
    }

    public static void queryTopic(String topic){
        ZkUtils zkUtils=null;
        try{
            zkUtils=zkUtils.apply(ZK_CONNECT , SESSION_TIMEOUT, CONNECT_TIMEOUT,
                    JaasUtils. isZkSecurityEnabled());
            TopicMetadata metadata= AdminUtils.fetchTopicMetadataFromZk(topic, zkUtils);
            System.out.println("----------------"+metadata.topic());
            List<PartitionMetadata> list=metadata.partitionMetadata();
            for (PartitionMetadata partitionMetadata:list){
                System.out.println("leader="+partitionMetadata.leader()+" partition="+partitionMetadata.partition()+" replicas="+partitionMetadata.replicas());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zkUtils.close();
        }
    }

    @Test
    public void createTopicTest(){
        createTopic(TOPIC,1,1,new Properties());
    }
    @Test
    public void modifyTopicTest(){
        modifyTopic(TOPIC,initConfig());
    }
    @Test
    public void addPartitionsTest(){
        addPartitions(TOPIC,2);
    }
    @Test
    public void delTopicTest(){
        delTopic(TOPIC);
    }
    @Test
    public void queryTopicTest(){
        queryTopic(TOPIC);
    }
}
