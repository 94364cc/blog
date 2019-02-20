package com.shop.ssm.aop;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/1/24.
 */
//定义注解的类型
@Target(value = ElementType.METHOD)
//定义注解保留多久[SOURCE,CLASS,RUNTIME]
@Retention(value = RetentionPolicy.RUNTIME)
//注释成为公共API的一部分
@Documented
public @interface PrivlegeInfo {
    /**
     * @Description功能Id的参数索引位置  默认为0，表示功能id在第一个参数的位置上，-1则表示未提供，无法进行校验
     */
    String name () default "";
}
