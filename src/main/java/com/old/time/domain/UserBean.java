package com.old.time.domain;

import java.util.List;

/**
 * 用户基本信息
 */
public class UserBean {

    /**
     * id
     */
    private String userId;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private List<PhoneInfoEntity> phoneInfoEntities;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}
