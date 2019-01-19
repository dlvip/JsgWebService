package com.old.time.repository;

import com.old.time.domain.MusicEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<MusicEntry, Integer> {

    /**
     * 根据chapterId获取章节
     *
     * @param chapterId
     * @return
     */
    MusicEntry findByChapterId(Integer chapterId);

    /**
     * 根据albumId分页查询音频列表
     *
     * @param albumId
     * @param pageable
     * @return
     */
    List<MusicEntry> findMusicEntriesByAlbumId(Integer albumId, Pageable pageable);

    /**
     * 根据albumId查询音频列表
     *
     * @param albumId
     * @return
     */
    List<MusicEntry> findMusicEntriesByAlbumId(Integer albumId);

    /**
     * 判断是否已经存在
     *
     * @param chapterId
     * @param albumId
     * @return
     */
    boolean existsByChapterIdAndAlbumId(Integer chapterId, Integer albumId);

}
