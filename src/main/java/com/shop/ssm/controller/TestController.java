package com.shop.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.ssm.pojo.Message;
import com.shop.ssm.pojo.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/2/26.
 */
@Controller
@RequestMapping("/")
public class TestController {
    /**
     * 测试返回异常信息
     * @return
     */
    @RequestMapping("/exception")
    public String returnExceptionInfo() {
        if (1 != 2) {
            // 用户民错误或不存在异常
            throw new BusinessRuntimeException(ResultCode.USERNAME_ERROR);
        }
        return "success";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message handleException(Exception e) {
        return Message.of(ResultCode.USERNAME_EMPTY);
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
