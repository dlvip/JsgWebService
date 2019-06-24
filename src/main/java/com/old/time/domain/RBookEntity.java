package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RBookEntity {

    public static RBookEntity getInstance(){
        RBookEntity rBookEntity = new RBookEntity();
        rBookEntity.id = 0;
        rBookEntity.aType="0";
        rBookEntity.title="异能少女重生：帝少夺吻99次";
        rBookEntity.author = "格格喵";
        rBookEntity.des_cribe = "她助渣男荣华富贵，最终被渣男开车撞死。重生回到十二岁，她发誓不再期盼虚无的爱情！开启异能掌控阴阳，趋吉避凶未卜先知，有仇报仇有恩...";
        rBookEntity.images_large = "https://images.bookbao99.net/coverpic/596/596531.jpg";
        rBookEntity.weight = "00";

        return rBookEntity;
    }

    public RBookEntity(){

    }


    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 书名
     */
    private String title;

    /**
     * 封面
     */
    private String images_large;

    /**
     * 推荐语
     */
    private String des_cribe;

    /**
     * 作者
     */
    private String author;

    /**
     * 图书类型
     * 男频 0：玄幻、1：奇幻、2：仙侠、3：悬疑、4：军旅、5：历史、6：灵异
     * 女频 10：现代言情、11：古代言情、12：穿越架空、13：总裁豪门、14：青春校园
     * 20：
     */
    private String aType;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 精选：00：重磅推荐、01：热门精选、02：都市·猎艳、03：暧昧·异能、04：小编·推荐
     * 男频：10：重磅推荐、11：热门精选、12：玄幻·奇幻、13：历史·军旅、14：游戏·竞技
     * 女频：20：重磅推荐、21：热门精选、22：现代·都市、23：灵异·穿越、24：美文·同人
     */
    private String weight;


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

    public String getImages_large() {
        return images_large;
    }

    public void setImages_large(String images_large) {
        this.images_large = images_large;
    }

    public String getDes_cribe() {
        return des_cribe;
    }

    public void setDes_cribe(String des_cribe) {
        this.des_cribe = des_cribe;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
