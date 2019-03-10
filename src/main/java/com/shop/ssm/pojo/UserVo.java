package com.shop.ssm.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2019/3/5.
 */
public class UserVo extends User{
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
