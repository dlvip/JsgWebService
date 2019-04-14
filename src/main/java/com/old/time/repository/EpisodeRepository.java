package com.old.time.repository;

import com.old.time.domain.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Integer> {

    /**
     * 查询集
     *
     * @param videoId
     * @return
     */
    List<EpisodeEntity> findEpisodeEntitiesByVideoId(Integer videoId);



}
