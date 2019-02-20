package com.shop.ssm.expection;

/**
 * Created by Administrator on 2019/2/1.
 */
public class CustomException  extends Exception  {
    private Integer code;

    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public CustomException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
