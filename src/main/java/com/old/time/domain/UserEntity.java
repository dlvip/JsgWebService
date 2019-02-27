package com.old.time.domain;

import javax.persistence.*;

@Entity
public class UserEntity {

    public UserEntity() {

    }

    /**
     * 用户基本信息
     *
     * @param userId
     * @param mobile
     * @param pasWord
     */
    public UserEntity(String userId, String mobile, String pasWord) {
        this.userId = userId;
        this.mobile = mobile;
        this.pasWord = pasWord;

    }

    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    private String userName;

    private String avatar;

    private String token;

    private String pasWord;

    private String mobile;

    private String birthday;

    /**
     * 0：女、1：男
     */
    @Column(length = 1)
    private Integer sex;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSex() {
        return sex;
    }

    private String vocation;

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasWord() {
        return pasWord;
    }

    public void setPasWord(String pasWord) {
        this.pasWord = pasWord;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer isSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


}
