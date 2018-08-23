package com.old.time.repository;

import com.old.time.domain.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity,Integer> {
    

}
