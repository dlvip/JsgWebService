package com.old.time.domain;

import com.old.time.utils.IdUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ArticleEntity {

    public ArticleEntity(){

    }

    /**
     * 文章
     *
     * @param picUrl
     * @param title
     * @param detailUrl
     * @param lookCount
     */
    public ArticleEntity(String picUrl, String title, String detailUrl, Integer lookCount) {
        this.id = IdUtils.getCurrentTimeMillis();
        this.picUrl = picUrl;
        this.title = title;
        this.detailUrl = detailUrl;
        this.lookCount = lookCount;

    }

    @Id
    @GeneratedValue
    private Integer id;

    private String picUrl;

    private String title;

    private String detailUrl;

    private Integer type;

    private Integer lookCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLookCount() {
        return lookCount;
    }

    public void setLookCount(Integer lookCount) {
        this.lookCount = lookCount;
    }
}
