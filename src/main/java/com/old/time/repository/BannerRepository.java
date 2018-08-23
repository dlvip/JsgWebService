package com.old.time.repository;

import com.old.time.domain.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<BannerEntity, Integer> {
}
