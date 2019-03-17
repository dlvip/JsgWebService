package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MsgCodeEntity {

    public static MsgCodeEntity getInstance(String mobile, String code) {

        return new MsgCodeEntity(mobile, code);
    }

    private MsgCodeEntity(String mobile, String code) {
        this.mobile = mobile;
        this.code = code;
        this.createTime = System.currentTimeMillis();
        this.endTime = System.currentTimeMillis() + 2 * 60 * 1000;

    }

    public MsgCodeEntity() {

    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 验证码
     */
    private String code;

    /**
     * 生成时间
     */
    private long createTime;

    /**
     * 过期时间
     */
    private long endTime;

    /**
     * 密码
     */
    private String pasWord;

    /**
     * 手机号
     */
    private String mobile;

    public String getPasWord() {
        return pasWord;
    }

    public void setPasWord(String pasWord) {
        this.pasWord = pasWord;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
