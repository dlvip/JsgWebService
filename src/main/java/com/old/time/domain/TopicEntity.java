package com.old.time.domain;

import com.old.time.utils.IdUtils;
import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TopicEntity {

    public TopicEntity() {

    }

    public TopicEntity(String userId, String topicTitle, String topicContent) {
        this.userId = userId;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
        this.createTime = TimeUtil.getCurrentTime();
        this.topicId = IdUtils.getCurrentTimeMillis();
        this.commentCount = 0;
        this.praiseCount = 0;

    }

    @Id
    @GeneratedValue
    private Integer id;

    private Integer topicId;

    private String topicTitle;

    private String topicContent;

    private String userId;

    private Integer praiseCount;

    private Integer commentCount;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
