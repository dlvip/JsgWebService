package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DownLoadEntity {

    public DownLoadEntity() {

    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 版本号
     */
    private Integer versionCode;

    /**
     * 描述
     */
    private String describeMsg;

    /**
     * 下载URl
     */
    private String url;

    /**
     * 是否强制升级
     */
    private Integer isForce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescribeMsg() {
        return describeMsg;
    }

    public void setDescribeMsg(String describeMsg) {
        this.describeMsg = describeMsg;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsForce() {
        return isForce;
    }

    public void setIsForce(Integer isForce) {
        this.isForce = isForce;
    }

    @Override
    public String toString() {
        return "DownLoadEntity{" +
                "versionCode=" + versionCode +
                ", describe='" + describeMsg + '\'' +
                ", url='" + url + '\'' +
                ", isForce=" + isForce +
                '}';
    }
}
