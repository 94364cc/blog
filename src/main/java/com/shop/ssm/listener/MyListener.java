package com.shop.ssm.listener;

import com.shop.ssm.dao.JedisClient;
import com.shop.ssm.dao.impl.JedisClientSingle;
import com.shop.ssm.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2018/10/31.
 */
public class MyListener implements ServletContextListener {
    private static WebApplicationContext context;
    private BlogService service;
    public void contextInitialized( ServletContextEvent event) {
        String str = null;
        System.out.println("step into init method");
        context= WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        service=context.getBean(BlogService.class);
        new Thread(){
            @Override
            public void run() {
//                service.sendEmail();
            }
        }.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("exec destory method");
    }
}
