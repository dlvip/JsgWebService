package com.old.time.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    private String userName;

    private String avatar;

    private String pasWord;

    private String mobile;

    private Date birthday;

    private boolean sex;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
