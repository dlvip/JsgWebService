package com.old.time.repository;

import com.old.time.domain.PhoneInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneInfoEntity, Integer> {

    /**
     * 查询手机号归属地
     *
     * @param phone
     * @return
     */
    PhoneInfoEntity findPhoneInfoEntityByPhone(String phone);

    /**
     * 判断手机号是否存在
     *
     * @param phone
     * @return
     */
    boolean existsPhoneInfoEntityByPhone(String phone);

}
