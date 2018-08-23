package com.old.time.service;

import com.old.time.domain.*;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.AlbumRepository;
import com.old.time.repository.CourseRepository;
import com.old.time.repository.MusicRepository;
import com.old.time.repository.VideoRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class JSGuangService {

    @Transactional
    public void insertTwo() {

    }

    /**
     * 视频是否存在
     *
     * @param optional
     */
    public Result getVideoEntity(Optional optional) throws RuntimeException {
        if (optional == null || optional.get() == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        return ResultUtil.success(optional.get());
    }

    /**
     * 保存视频
     *
     * @param videoEntity
     * @return
     */
    public Result saveVideoEntity(VideoEntity videoEntity, VideoRepository videoRepository) throws RuntimeException {
        if (videoEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        if ("".equals(videoEntity.getVideoTitle())
                || "".equals(videoEntity.getVideoUrl())
                || "".equals(videoEntity.getPicUrl())
                || "".equals(videoEntity.getTotalTime())) {

            throw new JSGRuntimeException(ResultEnum.DATA_FORMAT_ERROR);
        }

        return ResultUtil.success(videoRepository.save(videoEntity));
    }

    /**
     * 保存相册
     *
     * @param albumEntity
     * @return
     * @throws RuntimeException
     */
    public Result saveAlbumEntity(AlbumEntity albumEntity, AlbumRepository albumRepository) throws RuntimeException {
        if (albumEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        return ResultUtil.success(albumRepository.save(albumEntity));
    }

    /**
     * 添加课程
     *
     * @param mCourseEntity
     * @param courseRepository
     * @return
     */
    public Result saveCourseEntity(CourseEntity mCourseEntity, CourseRepository courseRepository) throws RuntimeException {
        if (mCourseEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(courseRepository.save(mCourseEntity));
    }

    /**
     * 添加章节
     *
     * @param musicEntry
     * @param musicRepository
     * @return
     * @throws RuntimeException
     */
    public Result saveMusicEntity(MusicEntry musicEntry, MusicRepository musicRepository) throws RuntimeException {
        if (musicEntry == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(musicRepository.save(musicEntry));
    }
}
