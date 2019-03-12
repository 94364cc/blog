package com.shop.ssm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2019/3/11.
 */
public class EmailCilent {
    protected final Logger logger = LoggerFactory.getLogger("EmailCilent");
    public static EmailCilent getInstance(){
        return new EmailCilent();
    }

    public void sendEmial(String from,String to,String title,String body){
        logger.info("send remind to subscriber with email");
    }
}
