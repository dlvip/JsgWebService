package com.old.time.domain;

import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class TopicEntity {

    /**
     * 实例话话题
     *
     * @param userId
     * @param topic
     * @param pic
     * @return
     */
    public static TopicEntity getInstance(String userId, String topic, String pic) {

        return new TopicEntity(userId, topic, pic);
    }

    private TopicEntity(String userId, String topic, String pic) {
        this.userId = userId;
        this.topic = topic;
        this.pic = pic;
        this.createTime = TimeUtil.getCurrentTime();

    }

    @Id
    @GeneratedValue
    private Integer id;

    private String pic;

    private String topic;

    private String userId;

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

    @Transient
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
