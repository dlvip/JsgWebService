package com.old.time.repository;

import com.old.time.domain.PhoneBeanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneBeanRepository extends JpaRepository<PhoneBeanEntity, String> {

    /**
     * 查询通讯录列表
     *
     * @param userId
     * @return
     */
    List<PhoneBeanEntity> findPhoneBeanEntitiesByUserId(String userId);

    /**
     * 查询单个通讯录信息
     *
     * @param userId
     * @param id
     * @return
     */
    PhoneBeanEntity findPhoneBeanEntityByUserIdAndId(String userId, String id);


}
