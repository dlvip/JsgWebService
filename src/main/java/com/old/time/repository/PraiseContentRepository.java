package com.old.time.repository;

import com.old.time.domain.PraiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PraiseContentRepository extends JpaRepository<PraiseEntity, Integer> {

    /**
     * 获取点赞数量
     *
     * @param contentId
     * @return
     */
    int countAllByContentId(Integer contentId);

    /**
     * 判断内容是否存在（是否点过赞）
     *
     * @param praiseType
     * @param userId
     * @param contentId
     * @return
     */
    boolean existsPraiseEntityByPraiseTypeAndUserIdAndContentId(String praiseType, String userId, Integer contentId);

    /**
     * 获取点赞实例
     *
     * @param praiseType
     * @param userId
     * @param contentId
     * @return
     */
    PraiseEntity findPraiseEntityByPraiseTypeAndUserIdAndContentId(String praiseType, String userId, Integer contentId);


}
