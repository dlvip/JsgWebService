package com.old.time.domain;

import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DynamicEntity {

    /**
     * 动态
     *
     * @param userId
     * @param images
     * @param content
     * @param topicId
     * @return
     */
    public static DynamicEntity getInstance(String userId, String content, String images, Integer topicId) {

        return new DynamicEntity(userId, content, images, topicId);
    }

    private DynamicEntity(String userId, String content, String images, Integer topicId) {
        this.userId = userId;
        this.content = content;
        this.images = images;
        this.topicId = topicId;
        this.createTime = TimeUtil.getCurrentTime();

    }

    public DynamicEntity() {

    }


    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    private String images;

    private String content;

    private Integer topicId;

    private String createTime;

    @Transient
    private String commentCount;

    @Transient
    private String praiseCount;

    @Transient
    private UserEntity userEntity;

    @Transient
    private TopicEntity topicEntity;

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
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
