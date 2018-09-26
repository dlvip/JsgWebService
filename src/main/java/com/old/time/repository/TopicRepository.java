package com.old.time.repository;

import com.old.time.domain.TopicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {

    /**
     * 获取话题对象
     *
     * @param topicId
     * @return
     */
    TopicEntity findTopicEntitiesByTopicId(Integer topicId);

    /**
     * 获取用户话题
     *
     * @param userId
     * @return
     */
    List<TopicEntity> findTopicEntitiesByUserId(String userId, Pageable pageable);

    /**
     * 判断是否存在
     *
     * @param topicId
     * @return
     */
    boolean existsByTopicId(Integer topicId);

    /**
     * 删除话题
     *
     * @param topicId
     * @return
     */
    boolean deleteTopicEntityByUserIdAndTopicId(String userId, Integer topicId);

}
