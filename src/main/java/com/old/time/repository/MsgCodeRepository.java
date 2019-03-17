package com.old.time.repository;

import com.old.time.domain.MsgCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgCodeRepository extends JpaRepository<MsgCodeEntity, Integer> {

    /**
     * 是否已经存在过
     *
     * @param mobile
     * @return
     */
    boolean existsByMobile(String mobile);

    /**
     * 根据收好查找验证码
     *
     * @param mobile
     * @return
     */
    MsgCodeEntity findByMobile(String mobile);


}
