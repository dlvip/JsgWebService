package com.old.time.domain;

import com.old.time.utils.IdUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BannerEntity {

    public BannerEntity() {

    }


    public BannerEntity(String title, String picUrl, String detailUrl) {
        this.id = IdUtils.getCurrentTimeMillis();
        this.title = title;
        this.picUrl = picUrl;
        this.detailUrl = detailUrl;
        this.type = 0;

    }

    @Id
    private Integer id;

    private Integer type;

    private String title;

    private String picUrl;

    private String detailUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
