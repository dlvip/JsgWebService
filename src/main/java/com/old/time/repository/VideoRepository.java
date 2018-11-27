package com.old.time.repository;

import com.old.time.domain.VideoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {


    /**
     * 查询视频列表
     *
     * @param id
     * @param pageable
     * @return
     */
    List<VideoEntity> findVideoEntitiesById(Integer id, Pageable pageable);

}
