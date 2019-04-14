package com.old.time.domain;

import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class VideoEntity {

    public static VideoEntity instance(String name
            , String detail
            , String pic
            , String country
            , String film
            , String type
            , String score
            , int episodes
            , int totalEpisodes) {

        return new VideoEntity(name
                , detail
                , pic
                , country
                , film
                , type
                , score
                , episodes
                , totalEpisodes);
    }

    private VideoEntity(String name
            , String detail
            , String pic
            , String country
            , String film
            , String type
            , String score
            , int episodes
            , int totalEpisodes) {
        this.name = name;
        this.detail = detail;
        this.pic = pic;
        this.country = country;
        this.film = film;
        this.type = type;
        this.score = score;
        this.episodes = episodes;
        this.totalEpisodes = totalEpisodes;
        this.createTime = TimeUtil.getCurrentTime();
        this.weight = 0;

    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String detail;

    /**
     * 封面
     */
    private String pic;

    /**
     * 国家（大陆/香港/澳门/台湾）
     */
    private String country;

    /**
     * 所属类型（电影/电视/动漫/综艺）
     */
    private String film;

    /**
     * 剧情类型（悬疑/惊悚/魔幻）
     */
    private String type;

    /**
     * 评分
     */
    private String score;

    /**
     * 更新进度（集）
     */
    private int episodes;

    /**
     * 总进度（集）
     */
    private int totalEpisodes;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 推荐权重
     */
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * 剧情列表
     */
    @Transient
    private List<EpisodeEntity> episodeEntities;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String videoType) {
        this.film = videoType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<EpisodeEntity> getEpisodeEntities() {
        return episodeEntities;
    }

    public void setEpisodeEntities(List<EpisodeEntity> episodeEntities) {
        this.episodeEntities = episodeEntities;
    }
}
