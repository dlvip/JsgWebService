package com.old.time.repository;

import com.old.time.domain.SignNameEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignNameRepository extends JpaRepository<SignNameEntity, Integer> {


    /**
     * 获取书签详情
     *
     * @param userId
     * @param id
     * @return
     */
    SignNameEntity findSignNameEntityByUserIdAndId(String userId, String id);


    /**
     * 获取某用户书签
     *
     * @param userId
     * @return
     */
    List<SignNameEntity> findSignNameEntitiesByUserId(String userId, Pageable pageable);

    /**
     * 获取图书下的所有书签
     *
     * @param bookId
     * @return
     */
    List<SignNameEntity> findSignNameEntitiesByBookId(Integer bookId, Pageable pageable);
}
