package com.old.time.repository;

import com.old.time.domain.DynamicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DynamicRepository extends JpaRepository<DynamicEntity, Integer> {

    /**
     * 判断是否存在
     *
     * @param id
     * @return
     */
    boolean existsDynamicEntityById(Integer id);

    /**
     * 获取动态详情
     *
     * @param id
     * @return
     */
    DynamicEntity findDynamicEntityById(Integer id);

    /**
     * 删除动态
     *
     * @param userId
     * @param id
     * @return
     */
    DynamicEntity deleteDistinctByUserIdAndId(String userId, Integer id);

    /**
     * 分页获取用户动态
     *
     * @param userId
     * @param pageable
     * @return
     */
    List<DynamicEntity> findDynamicEntitiesByUserId(String userId, Pageable pageable);

    /**
     * 分页获取用户动态
     *
     * @param topicId
     * @param pageable
     * @return
     */
    List<DynamicEntity> findDynamicEntitiesByTopicId(Integer topicId, Pageable pageable);

}
