package com.shop.ssm.pojo;

import java.util.Date;

public class Log {
    private Integer logId;

    private Integer userId;

    private String requestType;

    private String url;
    private Date createTime;

    public Log(Integer userId, String requestType, String url, Date createTime) {
        this.userId = userId;
        this.requestType = requestType;
        this.url = url;
        this.createTime = createTime;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}