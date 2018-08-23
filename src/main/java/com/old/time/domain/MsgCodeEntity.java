package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MsgCodeEntity {

    public MsgCodeEntity() {

    }


    /**
     * 验证码
     *
     * @param mobile
     * @param code
     * @param endTime
     */
    public MsgCodeEntity(String mobile, String code, long endTime, long createTime) {
        this.mobile = mobile;
        this.code = code;
        this.endTime = endTime;
        this.createTime = createTime;

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

    private String mobile;

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
