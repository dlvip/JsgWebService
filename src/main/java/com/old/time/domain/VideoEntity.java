package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VideoEntity {

    public static VideoEntity getInstance(Integer id, String picUrl, String title, String videoUrl) {
        VideoEntity mVideoEntity = new VideoEntity();
        mVideoEntity.id = id;
        mVideoEntity.picUrl = picUrl;
        mVideoEntity.videoTitle = title;
        mVideoEntity.videoUrl = videoUrl;

        return mVideoEntity;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String picUrl;

    private String videoUrl;

    private String videoTitle;

    private Integer totalTime;

    public VideoEntity() {

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "id=" + id +
                ", picUrl='" + picUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", totalTime=" + totalTime +
                '}';
    }
}
