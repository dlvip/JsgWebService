package com.old.time.repository;

import com.old.time.domain.CourseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    /**
     * 获取专辑信息
     *
     * @param albumId
     * @return
     */
    CourseEntity findByAlbumId(Integer albumId);

    /**
     * 获取专辑列表
     *
     * @param userId
     * @return
     */
    List<CourseEntity> findCourseEntitiesByUserId(String userId);

    /**
     * 获取用户专辑列表（分页）
     *
     * @param userId
     * @param pageable
     * @return
     */
    List<CourseEntity> findCourseEntitiesByUserId(String userId, Pageable pageable);

    /**
     * 获取专辑是否存在
     *
     * @param albumId
     * @return
     */
    boolean existsByAlbumId(Integer albumId);


}
