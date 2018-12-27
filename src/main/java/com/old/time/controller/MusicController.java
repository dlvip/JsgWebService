package com.old.time.controller;

import com.old.time.domain.CourseEntity;
import com.old.time.domain.MusicEntry;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.CourseRepository;
import com.old.time.repository.MusicRepository;
import com.old.time.utils.ResultUtil;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping(value = "jiushiguang/music")
public class MusicController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseController courseController;

    @PostMapping(value = "/addMusic")
    public Result addCourse(MusicEntry musicEntry) {

        return jSGuangService.saveMusicEntity(musicEntry, musicRepository);
    }

    @PostMapping(value = "/saveChapter")
    public Result saveChapter() {
        try {
            String path = MusicController.class.getResource("/assets").getPath();
            File jsonFile = ResourceUtils.getFile(path + "/courses.json");
            String json = FileUtils.readFileToString(jsonFile, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject.getJSONArray("albumResults");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String userId = "06l6pkk0";
                Integer albumId = jsonObject.getInt("albumId");
                boolean isExists = courseRepository.existsByAlbumId(albumId);
                if (!isExists) {
                    String albumTitle = jsonObject.getString("albumTitle");
                    String albumCover = jsonObject.getString("albumCover");
                    CourseEntity courseEntity = new CourseEntity(userId, albumId, albumTitle, albumCover);
                    courseController.addCourse(courseEntity);
                    logger.info("【保存成功 courseEntity = 】{}", courseEntity.toString());

                }
                jsonFile = ResourceUtils.getFile(path + "/" + String.valueOf(albumId) + ".json");
                json = FileUtils.readFileToString(jsonFile, "UTF-8");
                jsonObject = new JSONObject(json);
                jsonObject = jsonObject.getJSONObject("data");
                JSONArray array = jsonObject.getJSONArray("list");
                for (int j = 0; j < array.length(); j++) {
                    JSONObject musicObj = array.getJSONObject(j);
                    Integer chapterId = musicObj.getInt("trackId");
                    boolean isExist = musicRepository.existsByChapterIdAndAlbumId(chapterId, albumId);
                    if (!isExist) {
                        String url = musicObj.getString("playUrl64");
                        String picUrl = musicObj.getString("coverLarge");
                        String title = musicObj.getString("title");
                        Integer duration = musicObj.getInt("duration");
                        Integer orderNo = musicObj.getInt("orderNo");
                        MusicEntry musicEntry = new MusicEntry(chapterId, userId, albumId, url, picUrl, title, duration, orderNo);
                        musicRepository.save(musicEntry);
                        logger.info("【保存成功 MusicEntry = 】{}", musicEntry.toString());

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResultUtil.success();
    }

//    /**
//     * 添加章节
//     *
//     * @param userId
//     * @param albumId
//     * @param chapterId
//     * @param musicUrl
//     * @param musicPic
//     * @param musicTitle
//     * @param musicTime
//     * @param orderNo
//     * @return
//     */
//    @PostMapping(value = "/saveChapter")
//    public Result saveChapter(@RequestParam("userId") String userId, @RequestParam("albumId") Integer albumId, @RequestParam("chapterId") Integer chapterId, @RequestParam("musicUrl") String musicUrl, @RequestParam("musicPic") String musicPic, @RequestParam("musicTitle") String musicTitle, @RequestParam("musicTime") long musicTime, @RequestParam("orderNo") Integer orderNo) {
//        CourseEntity courseEntity = courseRepository.findByAlbumId(albumId);
//        if (courseEntity == null) {
//
//            throw new JSGNoSuchElementException(ResultEnum.USER_COURSE_NON);
//        }
//        if (!userId.equals(courseEntity.getUserId())) {
//
//            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_PERMISSION);
//        }
//        MusicEntry musicEntry = new MusicEntry(chapterId, userId, albumId, musicUrl, musicPic, musicTitle, musicTime, orderNo);
//
//        return ResultUtil.success(musicRepository.save(musicEntry));
//    }

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
        if (mMusicEntry == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        if (!musicEntry.getUserId().equals(mMusicEntry.getUserId())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_PERMISSION);
        }

        if (musicEntry.getPicUrl() != null) {
            mMusicEntry.setPicUrl(musicEntry.getPicUrl());

        }
        if (musicEntry.getTitle() != null) {
            mMusicEntry.setTitle(musicEntry.getTitle());

        }
        if (musicEntry.getUrl() != null) {
            mMusicEntry.setUrl(musicEntry.getUrl());

        }
        if (musicEntry.getDuration() != 0) {
            mMusicEntry.setDuration(musicEntry.getDuration());

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
