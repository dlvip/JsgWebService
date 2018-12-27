package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MusicEntry {

    public MusicEntry() {

    }


    public MusicEntry(Integer chapterId, String userId, Integer albumId, String url, String picUrl, String title, Integer duration, Integer orderNo) {
        this.chapterId = chapterId;
        this.userId = userId;
        this.albumId = albumId;
        this.url = url;
        this.picUrl = picUrl;
        this.title = title;
        this.duration = duration;
        this.orderNo = orderNo;

    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 章节id
     */
    private Integer chapterId;

    /**
     * 专辑id
     */
    private Integer albumId;

    /**
     * 播放地址
     */
    private String url;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 封面
     */
    private String picUrl;

    /**
     * 时长
     */
    private Integer duration;

    /**
     * 章节序号
     */
    private Integer orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "MusicEntry{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", chapterId=" + chapterId +
                ", albumId=" + albumId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", duration=" + duration +
                ", orderNo=" + orderNo +
                '}';
    }
}
