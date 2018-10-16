package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class GoodsEntity {

    public GoodsEntity() {

    }

    public static GoodsEntity getInstance(String userId, String picKey, String title, String price) {

        return new GoodsEntity(userId, picKey, title, price);
    }

    public GoodsEntity(String userId, String picKey, String title, String price) {
        this.userId = userId;
        this.picKey = picKey;
        this.title = title;
        this.price = price;
        this.isDispose = false;

    }


    @GeneratedValue
    private Integer goodsId;

    private String userId;

    private String picKey;

    private String title;

    private String price;

    private String detailId;

    private boolean isDispose;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getPicKey() {
        return picKey;
    }

    public void setPicKey(String picKey) {
        this.picKey = picKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public boolean getIsDispose() {
        return isDispose;
    }

    public void setIsDispose(boolean isDispose) {
        this.isDispose = isDispose;
    }
}
