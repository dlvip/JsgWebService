package com.old.time.controller;

import com.old.time.domain.VideoEntity;
import com.old.time.domain.Result;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.UserRepository;
import com.old.time.repository.VideoRepository;
import com.old.time.utils.ResultUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "jiushiguang/video")
public class VideoController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

//    /**
//     * 添加
//     *
//     * @return
//     */
//    @PostMapping(value = "/addVideo")
//    public Result videoAdd(@RequestParam("userId") String userId, @RequestParam("id") Integer id, @RequestParam("picUrl") String picUrl, @RequestParam("title") String title, @RequestParam("videoUrl") String videoUrl) {
//        boolean isUserExists = userRepository.existsByUserId(userId);
//        if (!isUserExists) {
//
//            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
//        }
//        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
//        if ("15093073252".equals(userEntity.getMobile())) {
//
//            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
//        }
//        if (0 == id || "".equals(picUrl) || "".equals(title) || "".equals(videoUrl)) {
//
//            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
//        }
//        VideoEntity videoEntity = VideoEntity.getInstance(id, picUrl, title, videoUrl);
//        logger.info("【保存成功】{}", videoEntity.toString());
//        return ResultUtil.success(videoRepository.save(videoEntity));
//    }

    /**
     * 添加
     *
     * @return
     */
    @PostMapping(value = "/addVideo")
    public void videoAdd() {
        try {
            String path = VideoController.class.getResource("/assets").getPath();
            File jsonFile = ResourceUtils.getFile(path + "/mp4.json");
            String json = FileUtils.readFileToString(jsonFile, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                int id = jsonObject1.getInt("d_id");
                String picUrl = jsonObject1.getString("d_pic");
                String title = jsonObject1.getString("d_name");
                String videoUrl = "http://iqiyi.qq-zuidazy.com//20181115//768_ea6fbf2b//index.m3u8";
                VideoEntity videoEntity = VideoEntity.getInstance(id, picUrl, title, videoUrl);
                videoRepository.save(videoEntity);

                logger.info("【保存成功】{}", videoEntity.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @PostMapping(value = "/delVideo")
    public void videoDelete(@RequestParam("id") Integer id) throws JSGRuntimeException {
        videoRepository.deleteById(id);
    }

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getVideoById")
    public Result getVideoById(@RequestParam("id") Integer id) throws RuntimeException {
        Optional<VideoEntity> optional = videoRepository.findById(id);
        return jSGuangService.getVideoEntity(optional);
    }

    /**
     * 查询视频列表
     *
     * @return
     */
    @PostMapping(value = "/getVideos")
    @Override
    public Result getControllerList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Page<VideoEntity> page = videoRepository.findAll(PageRequest.of(pageNum, pageSize));
        List<VideoEntity> videoEntities = page.getContent();
        return ResultUtil.success(videoEntities);
    }
}
