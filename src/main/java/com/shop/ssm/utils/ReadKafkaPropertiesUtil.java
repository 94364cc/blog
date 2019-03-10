package com.shop.ssm.utils;

/**
 * Created by Administrator on 2019/3/7.
 */
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * 加载 配置的 kafka.properties 文件
 *
 */
public class ReadKafkaPropertiesUtil {

    /**
     * 属性
     */
    private static Properties properties;

    /**
     * 读取kafka.properties
     */
    static {
        properties = new Properties();
        String path = ReadKafkaPropertiesUtil.class.getResource("/").getFile().toString() + "kafka.properties";
        FileInputStream fis =null;
        try {
            fis= new FileInputStream(new File(path));
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("kafka init wrong");
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取kafka的配置信息
     *
     * @return
     */
    public static Properties getProperties() {
        return properties;
    }
}