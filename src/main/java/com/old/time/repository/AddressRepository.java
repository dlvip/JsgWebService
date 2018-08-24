package com.old.time.repository;

import com.old.time.domain.AddressEntity;
import com.old.time.domain.MusicEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    /**
     * 根据addressId查找地址
     *
     * @param addressId
     * @return
     */
    AddressEntity findByAddressId(Integer addressId);

    /**
     * 查询地址信息列表
     *
     * @param userId
     * @return
     */
    List<AddressEntity> findAddressEntitiesByUserId(String userId);

    /**
     * 根据userId分页查询地址
     *
     * @param userId
     * @param pageable
     * @return
     */
    List<AddressEntity> findAddressEntitiesByUserId(String userId, Pageable pageable);


}
