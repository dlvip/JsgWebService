package com.old.time.repository;

import com.old.time.domain.GoodsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {

    /**
     * 获取用户宝贝列表
     *
     * @param userId
     * @return
     */
    List<GoodsEntity> findGoodsEntitiesByUserId(String userId, Pageable pageable);

    /**
     * 获取所有宝贝列表
     *
     * @param isDispose
     * @return
     */
    List<GoodsEntity> findGoodsEntitiesByIsDispose(boolean isDispose, Pageable pageable);

    /**
     * 查找宝贝信息
     *
     * @param goodsId
     * @return
     */
    GoodsEntity findGoodsEntityByGoodsId(Integer goodsId);

}
