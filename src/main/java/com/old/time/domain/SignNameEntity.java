package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SignNameEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    private String picUrl;

    private String content;

    private String shareCount;

    private String paiseCount;

    private String creatTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getPaiseCount() {
        return paiseCount;
    }

    public void setPaiseCount(String paiseCount) {
        this.paiseCount = paiseCount;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Transient
    private Integer isPaise;

    public Integer getIsPaise() {
        return isPaise;
    }

    public void setIsPaise(Integer isPaise) {
        this.isPaise = isPaise;
    }

    @Transient
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
