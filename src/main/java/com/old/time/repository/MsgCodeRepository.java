package com.old.time.repository;

import com.old.time.domain.MsgCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgCodeRepository extends JpaRepository<MsgCodeEntity, Integer> {

    /**
     * 根据收好查找验证码
     *
     * @param mobile
     * @return
     */
    MsgCodeEntity findByMobile(String mobile);



}
