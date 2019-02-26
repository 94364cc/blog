package com.shop.ssm.pojo;

/**
 * Created by Administrator on 2018/10/24.
 */
public class Message {
    private String status;
    private String message;
    private Object data;
    public Message(){}
    public Message(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Message Ok(){
        return new Message("200","success",null);
    }

    public static Message of(ResultCode resultCode) {
        return new Message(resultCode.getCode(),resultCode.getMsg(),null);
    }
}
