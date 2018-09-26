package com.old.time.domain;

import com.old.time.utils.IdUtils;
import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CommentEntity {

    public CommentEntity() {


    }

    public CommentEntity(String userId, Integer topicId, String comment) {
        this.userId = userId;
        this.topicId = topicId;
        this.comment = comment;
        this.createTime = TimeUtil.getCurrentTime();
        this.commentId = IdUtils.getCurrentTimeMillis();
        this.praiseCount = 0;

    }

    @Id
    @GeneratedValue
    private Integer id;

    private Integer commentId;

    private String userId;

    private Integer topicId;

    private String comment;

    private Integer praiseCount;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Transient
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
