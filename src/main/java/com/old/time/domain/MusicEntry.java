package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MusicEntry {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private Integer albumId;

    private String musiceUrl;

    private String musiceTitle;

    private String musicPic;

    private long musiceTime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getMusiceUrl() {
        return musiceUrl;
    }

    public void setMusiceUrl(String musiceUrl) {
        this.musiceUrl = musiceUrl;
    }

    public String getMusiceTitle() {
        return musiceTitle;
    }

    public void setMusiceTitle(String musiceTitle) {
        this.musiceTitle = musiceTitle;
    }

    public long getMusiceTime() {
        return musiceTime;
    }

    public void setMusiceTime(long musiceTime) {
        this.musiceTime = musiceTime;
    }
}
