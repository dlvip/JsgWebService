package com.old.time.repository;

import com.old.time.domain.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Integer> {


}
