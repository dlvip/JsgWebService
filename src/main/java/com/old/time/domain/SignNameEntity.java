package com.old.time.domain;

import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SignNameEntity {

    public SignNameEntity() {

    }

    public static SignNameEntity getInstance(String userId, String picUrl, String content, Integer bookId) {

        return new SignNameEntity(userId, picUrl, content, bookId);
    }

    private SignNameEntity(String userId, String picUrl, String content, Integer bookId) {
        this.userId = userId;
        this.picUrl = picUrl;
        this.content = content;
        this.bookId = bookId;
        this.creatTime = TimeUtil.getCurrentTime();
        this.shareCount = 0;
        this.paiseCount = 0;

    }

    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    private String picUrl;

    private String content;

    private Integer shareCount;

    private String creatTime;

    private Integer bookId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

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

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getPaiseCount() {
        return paiseCount;
    }

    public void setPaiseCount(Integer paiseCount) {
        this.paiseCount = paiseCount;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Transient
    private Integer paiseCount;

    @Transient
    private boolean isPaise;

    public boolean getIsPaise() {
        return isPaise;
    }

    public void setIsPaise(boolean isPaise) {
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

    @Transient
    private BookEntity bookEntity;

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }
}
