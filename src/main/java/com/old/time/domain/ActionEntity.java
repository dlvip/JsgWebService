package com.old.time.domain;

import com.old.time.utils.IdUtils;
import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ActionEntity {

    public ActionEntity() {

    }

    public ActionEntity(String userId, String title, String pic, String url, String brief, String longitude, String latitude, String address, String startTime, String endTime) {
        this.userId = userId;
        this.actionId = IdUtils.getCurrentTimeMillis();
        this.title = title;
        this.pic = pic;
        this.url = url;
        this.brief = brief;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.createTime = TimeUtil.getCurrentTime();
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = 0.0;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private Integer actionId;

    private String userId;

    private String pic;

    private String url;

    private String title;

    private String brief;

    private String createTime;

    private String startTime;

    private String endTime;

    private String longitude;

    private String latitude;

    private String address;

    private double price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
