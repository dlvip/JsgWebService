package com.old.time.repository;

import com.old.time.domain.VideoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
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
     * 通过名称搜索
     *
     * @param name
     * @return
     */
    List<VideoEntity> findVideoEntitiesByNameIsLike(String name);

    /**
     * 推荐查询
     *
     * @param weight
     * @return
     */
    List<VideoEntity> findDistinctFirstByWeight(int weight);

    /**
     * 根据类型查询
     *
     * @param weight
     * @param pageable
     * @return
     */
    List<VideoEntity> findVideoEntitiesByWeight(Integer weight, Pageable pageable);


    /**
     * 根据类型查询
     *
     * @param weight
     * @return
     */
    List<VideoEntity> findVideoEntitiesByWeight(int weight);


    /**
     * 类型查询
     *
     * @param type
     * @return
     */
    List<VideoEntity> findVideoEntitiesByTypeLike(String type, Pageable pageable);


    /**
     * 根据区域查询
     *
     * @param country
     * @param pageable
     * @return
     */
    List<VideoEntity> findVideoEntitiesByCountryLike(String country, Pageable pageable);

    /**
     * 根据时间查询
     *
     * @param createTime
     * @param pageable
     * @return
     */
    List<VideoEntity> findVideoEntitiesByCreateTimeLike(String createTime, Pageable pageable);

    /**
     * 检索
     *
     * @param type       类型
     * @param createTime 时间
     * @param pageable   分页
     * @return
     */
    List<VideoEntity> findVideoEntitiesByTypeContainingAndCreateTimeContaining(String type, String createTime, Pageable pageable);

    /**
     * 检索
     *
     * @param type     类型
     * @param country  国家
     * @param pageable 分页
     * @return
     */
    List<VideoEntity> findVideoEntitiesByTypeContainingAndCountryContaining(String type, String country, Pageable pageable);

    /**
     * 检索
     *
     * @param country    国家
     * @param createTime 时间
     * @param pageable   分页
     * @return
     */
    List<VideoEntity> findVideoEntitiesByCountryContainingAndCreateTimeContaining(String country, String createTime, Pageable pageable);

    /**
     * 检索
     *
     * @param type       类型
     * @param country    区域
     * @param createTime 时间
     * @param pageable   分页
     * @return
     */
    List<VideoEntity> findVideoEntitiesByTypeContainingAndCountryContainingAndCreateTimeContaining(String type, String country, String createTime, Pageable pageable);

    /**
     * 视频类型查询
     *
     * @param film
     * @return
     */
    List<VideoEntity> findVideoEntitiesByFilm(String film);

}
