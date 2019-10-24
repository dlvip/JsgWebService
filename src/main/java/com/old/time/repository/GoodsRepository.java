package com.old.time.repository;

import com.old.time.domain.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {

    boolean existsGoodsEntityByGoodsId(String goodsId);

    GoodsEntity findGoodsEntityByGoodsId(String goodsId);
}
