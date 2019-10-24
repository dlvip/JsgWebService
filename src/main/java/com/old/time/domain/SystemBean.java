package com.old.time.domain;

public class SystemBean {

    /**
     * 版本号
     */
    private int versionCode;

    /**
     * 描述
     */
    private String describe;

    /**
     * 下载URl
     */
    private String url;

    /**
     * 是否强制升级
     */
    private int isForce;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }
}
