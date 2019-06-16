package com.old.time.repository;

import com.old.time.domain.RBookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RBookRepository extends JpaRepository<RBookEntity, Integer> {

    /**
     * 获取图书列表
     *
     * @param weight
     * @param pageable
     * @return
     */
    List<RBookEntity> findRBookEntitiesByWeight(String weight, Pageable pageable);

    /**
     * @param a_type
     * @param pageable
     * @return
     */
    List<RBookEntity> findRBookEntitiesByA_type(String a_type, Pageable pageable);

    /**
     * 获取图书信息
     *
     * @param id
     * @return
     */
    RBookEntity findRBookEntityById(Integer id);

}
