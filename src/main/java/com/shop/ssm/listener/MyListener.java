package com.shop.ssm.listener;

import com.shop.ssm.kafka.Consumer;
import com.shop.ssm.service.PostService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2018/10/31.
 */
public class MyListener implements ServletContextListener {
    private static WebApplicationContext context;
    private PostService service;
    public void contextInitialized( ServletContextEvent event) {
        //redis 消息队列实现
//        String str = null;
//        System.out.println("step into init method");
//        context= WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
//        service=context.getBean(PostService.class);
//        new Thread(){
//            @Override
//            public void run() {
////                service.sendEmail();
//            }
//        }.start();

        //kafka消息队列实现
//        new Thread(new Consumer(event)).start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("exec destory method");
    }
}
