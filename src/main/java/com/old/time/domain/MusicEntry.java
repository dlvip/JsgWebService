package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MusicEntry {

    public MusicEntry() {

    }


    public MusicEntry(Integer chapterId, String userId, Integer albumId, String musicUrl, String musicPic, String musicTitle, long musicTime, Integer orderNo) {
        this.chapterId = chapterId;
        this.id = chapterId;
        this.userId = userId;
        this.albumId = albumId;
        this.musicUrl = musicUrl;
        this.musicPic = musicPic;
        this.musicTitle = musicTitle;
        this.musicTime = musicTime;
        this.orderNo = orderNo;

    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 章节id
     */
    private Integer chapterId;

    private Integer albumId;

    private String musicUrl;

    private String musicTitle;

    private String musicPic;

    private long musicTime;

    private String userId;

    /**
     * 章节序号
     */
    private Integer orderNo;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(long musicTime) {
        this.musicTime = musicTime;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public void setMusicPic(String musicPic) {
        this.musicPic = musicPic;
    }

    public String getMusicPic() {
        return musicPic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }
}
