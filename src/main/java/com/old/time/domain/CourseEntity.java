package com.old.time.domain;

import javax.persistence.*;

@Entity
public class CourseEntity {

    public CourseEntity() {


    }

    /**
     * 创建专辑
     *
     * @param userId
     * @param albumId
     * @param title
     * @param coursePic
     */
    public CourseEntity(String userId, Integer albumId, String title, String coursePic) {
        this.userId = userId;
        this.albumId = albumId;
        this.title = title;
        this.coursePic = coursePic;

    }

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户di
     */
    private String userId;

    /**
     * 专辑id
     */
    private Integer albumId;

    private String title;

    private String coursePic;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    
}
