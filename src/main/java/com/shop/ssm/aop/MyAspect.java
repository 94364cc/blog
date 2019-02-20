package com.shop.ssm.aop;

import com.alibaba.fastjson.JSON;
import com.shop.ssm.expection.CustomException;
import com.shop.ssm.service.AuthService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.rmi.AccessException;
import java.util.*;

/**
 * Created by Administrator on 2019/1/17.
 */
//@Aspect
//@Component
public class MyAspect {
//    @Autowired
//    AuthService service;
//    final static Map<String,Integer> CACHE=new HashMap<String, Integer>(){{
//       put("/user",1);
//        put("/authority",2);
//    }};

//    @Pointcut("execution(* com.shop.ssm.service.impl.*.insert*(..)) " +
//            "|| execution(* com.shop.ssm.service.impl.*.update*(..))" +
//            "|| execution(* com.shop.ssm.service.impl.*.delete*(..))"
//    )
//    public void myMethod() {};


//    //所有的方法，如果没有在数据库中注册，会自动注册
//    @Pointcut("execution(* com.shop.ssm.controller.*.*(..)) && !execution(*  com.shop.ssm.controller.LoginContrller.*(..)) ")
//    public void registMethod(){};

//    @Before("registMethod()")
//    public void registMethod(JoinPoint joinPoint){
//        HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
//        String url=request.getRequestURI().toString();
//        String userId= (String) request.getSession().getAttribute("userId");
////        if(userId==null || userId.equals("")) {
////
////        }
//    }



//    @Around("myMethod()")
//    public void beforeMyMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = (HttpSession) request.getSession();
//        String status = (String) session.getAttribute("status");
//        if(!status.equals("1")){
//            System.out.println("用户状态session过期");
//        }
//
//        System.out.println("@Before：模拟权限检查...");
//        System.out.println("@Before：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
//        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
//
//        System.out.println("before myMethod");

//        Class targetClass = joinPoint.getTarget().getClass();
//        String methodName = joinPoint.getSignature().getName();
//
//        //得到访问该方法的权限
//        String methodAccess = AnnotationParse.parse(targetClass, methodName,joinPoint.getArgs());
//
//        boolean flag = false;
//        //遍历用户所有权限，查看是否有访问该方法的权限
//        for (Privilege privilege : privileges) {
//            //该用户能访问目标方法
//            if (methodAccess.equals(privilege.getName())) {
//                flag = true;
//            }
//        }
//        if (flag) {
//            //开放访问权限
//            joinPoint.proceed();
//        } else {
//            System.out.println("无权限访问！");
//        }
//    }
//    @After("myMethod()")
//    public void afterMyMethod(){
//        System.out.println("after myMthod");
//    }
//
//    @AfterReturning("execution(* com.shop.ssm.service.impl.*.insert*(..))")
//    public void afterReturning(){
//        System.out.println("after return");
//    }
//
//    @AfterThrowing("execution(* com.shop.ssm.service.impl.*.insert*(..))")
//    public void afterThrowing(){
//        System.out.println("after throwing");
//    }

//    @Around("myMethod()")
//    public void aroundMethod(ProceedingJoinPoint pjp){
//        System.out.println(pjp.getSignature().getDeclaringType());
//        System.out.println(pjp.getSignature().getName());
//        System.out.println(pjp.getSignature().getDeclaringTypeName());
//        System.out.println(pjp.getSignature().getModifiers());
//    }


    //获取需要代理的方法
    private Method getSourceMethod(JoinPoint point){
        Method proxyMethod =((MethodSignature)point.getSignature()).getMethod();
        try {
            return point.getTarget().getClass().getMethod(proxyMethod.getName(),proxyMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取ip
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
    /**
     * 返回数据
     * @param retVal
     * @return
     */
    private String postHandle(Object retVal) {
        if(null == retVal){
            return "";
        }
        return JSON.toJSONString(retVal);
    }

    /**
     * 入参数据
     * @param joinPoint
     * @param request
     * @return
     */
    private String preHandle(ProceedingJoinPoint joinPoint,HttpServletRequest request) {
        String reqParam = "";
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Annotation[] annotations = targetMethod.getAnnotations();
        for (Annotation annotation : annotations) {
            //此处可以改成自定义的注解
            if (annotation.annotationType().equals(RequestMapping.class)) {
                reqParam = JSON.toJSONString(request.getParameterMap());
                break;
            }
        }
        return reqParam;
    }

}
