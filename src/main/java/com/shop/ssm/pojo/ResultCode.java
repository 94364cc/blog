package com.shop.ssm.pojo;

/**
 * Created by Administrator on 2019/2/26.
 */
public enum  ResultCode {
    /**
     * 成功
     */
    SUCCESS("200", "success"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR("1001", "unkonwn error"),

    /**
     * 用户名错误或不存在
     */
    USERNAME_OR_PASSWORD_ERROR("1002", "username or  password is not available"),

    /**
     * 用户名不能为空
     */
    USERNAME_OR_PASSWORD_EMPTY("1003", "username or password can not be empty"),
    /**
     * 用户名已经存在
     */
    USERNAME_ALREADY_EXISTS("1004", "username is already exist"),
    /**
     * 用户名已经存在
     */
    PASSWORD_INVAILD("1006", "password must include word and number!"),
    /**
     * kafka 消费报错
     */
    KAFKA_PROCDUCE_WRONG("2001", "something wrong happends whene send messages to kafka");


    /**
     * 结果码
     */
    private String code;

    /**
     * 结果码描述
     */
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
