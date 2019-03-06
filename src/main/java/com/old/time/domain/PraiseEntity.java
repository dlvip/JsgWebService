package com.old.time.domain;

import com.old.time.utils.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PraiseEntity {

    private PraiseEntity(String userId, String praiseType, Integer contentId) {
        this.userId = userId;
        this.praiseType = praiseType;
        this.contentId = contentId;
        this.createTime = TimeUtil.getCurrentTime();
    }

    public static final String PRAISE_TYPE_0 = "0";

    /**
     * 创建点赞实例
     *
     * @param userId
     * @param praiseType
     * @param contentId
     * @return
     */
    public static PraiseEntity getInstance(String userId, String praiseType, Integer contentId) {

        return new PraiseEntity(userId, praiseType, contentId);
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    /**
     * 所属类别 0、打卡
     */
    private String praiseType;

    /**
     * 所属内容id
     */
    private Integer contentId;

    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getPraiseType() {
        return praiseType;
    }

    public void setPraiseType(String praiseType) {
        this.praiseType = praiseType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}
