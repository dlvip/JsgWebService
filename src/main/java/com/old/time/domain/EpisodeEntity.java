package com.old.time.domain;

import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 剧情内容
 */
@Entity
public class EpisodeEntity {

    public EpisodeEntity(){

    }

    /**
     * 单集信息
     *
     * @param videoId
     * @param url
     * @return
     */
    public static EpisodeEntity instance(Integer videoId, String url) {

        return new EpisodeEntity(videoId, url);
    }

    private EpisodeEntity(Integer videoId, String url) {
        this.videoId = videoId;
        this.url = url;
        this.createTime = TimeUtil.getCurrentTime();

    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 视频信息id
     */
    private Integer videoId;

    /**
     * 播放地址
     */
    private String url;

    /**
     * 创建时间
     */
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
