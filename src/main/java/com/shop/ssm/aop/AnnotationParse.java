package com.shop.ssm.aop;

import com.shop.ssm.pojo.User;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/1/24.
 */
public class AnnotationParse {
    /**
     * targetClass  目标类的class字节码文件
     * methodName 在客户端调用哪个 方法，methodname
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static String parse(Class targetClass,String mthodName,Object[] args) throws NoSuchMethodException, SecurityException {

        String methodAccess = "";
        /**
         * 该方法没有参数
         */
        Method method = targetClass.getMethod(mthodName,User.class);
        //判断传入的字节码文件的方法上是否 有 PrivilegeInfo注解
        if (method.isAnnotationPresent(PrivlegeInfo.class)) {
            //得到方法上面的注解
            PrivlegeInfo privlegeInfo = method.getAnnotation(PrivlegeInfo.class);
            methodAccess = privlegeInfo.name();
        }
        return methodAccess;
    }
}
