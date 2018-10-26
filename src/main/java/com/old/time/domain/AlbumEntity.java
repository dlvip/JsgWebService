package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AlbumEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String picUrl;

    private Double price;

    private Integer buyCount;

    private String title;

    private String albumUrl;

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
