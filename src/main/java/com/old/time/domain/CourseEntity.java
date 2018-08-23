package com.old.time.domain;

import javax.persistence.*;

@Entity
public class CourseEntity  {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户di
     */
    private Integer userId;

    /**
     * 课程id
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }
}
