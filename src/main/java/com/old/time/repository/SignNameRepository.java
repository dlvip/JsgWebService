package com.old.time.repository;

import com.old.time.domain.SignNameEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignNameRepository extends JpaRepository<SignNameEntity, Integer> {


    SignNameEntity findSignNameEntityByUserIdAndId(String userId, String id);


    /**
     * 获取某用户书签
     *
     * @param userId
     * @return
     */
    List<SignNameEntity> findSignNameEntitiesByUserId(String userId, Pageable pageable);
}
