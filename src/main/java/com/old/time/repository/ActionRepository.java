package com.old.time.repository;

import com.old.time.domain.ActionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<ActionEntity, Integer> {

    /**
     * 查找活动列表(分页)
     *
     * @param userId
     * @return
     */
    List<ActionEntity> findActionEntityByUserId(String userId, Pageable pageable);

    /**
     * 获取活动详情
     *
     * @param actionId
     * @return
     */
    ActionEntity findActionEntityByActionId(Integer actionId);

    /**
     * 活动是否存在
     *
     * @param actionId
     * @return
     */
    boolean existsActionEntityByActionId(Integer actionId);


}
