package com.shop.ssm.pojo;

import java.util.Date;

public class Post {
    private Integer id;

    private Integer userId;

    private String title;

    private String label;

    private String content;

    private Date createTime;

    public Post() {
    }
    public Post(Integer id, Integer userId, String title, String label, String content,Date createTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.label = label;
        this.content = content;
        this.createTime=new Date();
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}