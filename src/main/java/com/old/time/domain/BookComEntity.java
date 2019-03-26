package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class BookComEntity {

    public BookComEntity() {


    }

    private BookComEntity(String userId, Integer bookId, String comment) {
        this.userId = userId;
        this.bookId = bookId;
        this.comment = comment;

    }

    public static BookComEntity getInstance(String userId, Integer bookId, String comment) {

        return new BookComEntity(userId, bookId, comment);
    }

    @Id
    @GeneratedValue
    private Integer id;

    private Integer bookId;

    private String userId;

    private String comment;

    @Transient
    private String praiseCount;

    @Transient
    private UserEntity userEntity;

    @Transient
    private BookEntity bookEntity;

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(String praiseCount) {
        this.praiseCount = praiseCount;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
