package com.old.time.repository;

import com.old.time.domain.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {

    /**
     * 查询名字是否存在
     *
     * @param name
     * @return
     */
    boolean existsVideoEntityByName(String name);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    VideoEntity findVideoEntityById(Integer id);

    /**
     * 推荐查询
     *
     * @param weight
     * @return
     */
    List<VideoEntity> findDistinctFirstByWeight(int weight);

    /**
     * 类型查询
     *
     * @param type
     * @return
     */
    List<VideoEntity> findVideoEntitiesByType(String type);

    /**
     * 视频类型查询
     *
     * @param film
     * @return
     */
    List<VideoEntity> findVideoEntitiesByFilm(String film);

    /**
     * 区域查找
     *
     * @param country
     * @return
     */
    List<VideoEntity> findVideoEntitiesByCountry(String country);
}
