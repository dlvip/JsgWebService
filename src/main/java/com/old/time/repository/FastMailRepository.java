package com.old.time.repository;

import com.old.time.domain.FastMailEnity;
import com.old.time.domain.PhoneInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FastMailRepository extends JpaRepository<FastMailEnity, Integer> {

    /**
     * 判断是否存在
     *
     * @param id
     * @return
     */
    boolean existsFastMailEnitieById(String id);
}
