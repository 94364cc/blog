package com.shop.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/2/1.
 */
@ControllerAdvice
public class GlobalExceptionResolver{
    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message handleException(Exception e) {
        return Message.of(ResultCode.UNKNOWN_ERROR);
    }

    /**
     * 处理所有业务异常
     *
     * @param e 业务异常
     * @return json结果
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseBody
    public Object handleOpdRuntimeException(BusinessRuntimeException e) {
        JSONObject jsonObj = (JSONObject) JSON.toJSON(Message.of(e.getResultCode()));
        return jsonObj.toString();
    }

}
