package com.old.time.controller;

import com.old.time.domain.CourseEntity;
import com.old.time.domain.MusicEntry;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.CourseRepository;
import com.old.time.repository.MusicRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/music")
public class MusicController extends BaseController {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping(value = "/addMusic")
    public Result addCourse(MusicEntry musicEntry) {

        return jSGuangService.saveMusicEntity(musicEntry, musicRepository);
    }

    /**
     * 添加章节
     *
     * @param userId
     * @param albumId
     * @param chapterId
     * @param musicUrl
     * @param musicPic
     * @param musicTitle
     * @param musicTime
     * @param orderNo
     * @return
     */
    @PostMapping(value = "/saveChapter")
    public Result saveChapter(@RequestParam("userId") String userId, @RequestParam("albumId") Integer albumId, @RequestParam("chapterId") Integer chapterId, @RequestParam("musicUrl") String musicUrl, @RequestParam("musicPic") String musicPic, @RequestParam("musicTitle") String musicTitle, @RequestParam("musicTime") long musicTime, @RequestParam("orderNo") Integer orderNo) {
        CourseEntity courseEntity = courseRepository.findByAlbumId(albumId);
        if (courseEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_COURSE_NON);
        }
        if (!userId.equals(courseEntity.getUserId())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_PERMISSION);
        }
        MusicEntry musicEntry = new MusicEntry(chapterId, userId, albumId, musicUrl, musicPic, musicTitle, musicTime, orderNo);

        return ResultUtil.success(musicRepository.save(musicEntry));
    }

    /**
     * 修改章节
     *
     * @return
     */
    @PostMapping(value = "/updateChapter")
    public Result updateChapter(MusicEntry musicEntry) {
        if (musicEntry == null || musicEntry.getUserId() == null || musicEntry.getAlbumId() == null || musicEntry.getChapterId() == null) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        MusicEntry mMusicEntry = musicRepository.findByChapterId(musicEntry.getChapterId());
        if(mMusicEntry == null){

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        if (!musicEntry.getUserId().equals(mMusicEntry.getUserId())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_PERMISSION);
        }

        if (musicEntry.getMusicPic() != null) {
            mMusicEntry.setMusicPic(musicEntry.getMusicPic());

        }
        if (musicEntry.getMusicTitle() != null) {
            mMusicEntry.setMusicTitle(musicEntry.getMusicTitle());

        }
        if (musicEntry.getMusicUrl() != null) {
            mMusicEntry.setMusicUrl(musicEntry.getMusicUrl());

        }
        if (musicEntry.getMusicTime() != 0) {
            mMusicEntry.setMusicTime(musicEntry.getMusicTime());

        }
        if (0 != musicEntry.getOrderNo()) {
            mMusicEntry.setOrderNo(musicEntry.getOrderNo());

        }

        return ResultUtil.success(musicRepository.save(mMusicEntry));
    }

    /**
     * 获取章节列表（分页）
     *
     * @param albumId
     * @return
     */
    @PostMapping(value = "/getChapters")
    public Result getChapters(@RequestParam("albumId") Integer albumId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isCourseExists = courseRepository.existsByAlbumId(albumId);
        if (!isCourseExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_COURSE_NON);
        }

        return ResultUtil.success(musicRepository.findMusicEntriesByAlbumId(albumId, PageRequest.of(pageNum, pageSize)));
    }

    @PostMapping(value = "/getMusicList")
    @Override
    public Result getControllerList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {

        return ResultUtil.success(musicRepository.findAll());
    }

}
