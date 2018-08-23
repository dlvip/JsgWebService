package com.old.time.controller;

import com.old.time.domain.VideoEntity;
import com.old.time.domain.Result;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.VideoRepository;
import com.old.time.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "jiushiguang/video")
public class VideoController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoRepository videoRepository;

    /**
     * 添加
     *
     * @return
     */
    @PostMapping(value = "/addVideo")
    public Result videoAdd(VideoEntity videoEntity) {
        return jSGuangService.saveVideoEntity(videoEntity, videoRepository);
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
    public Result getControllerList() {
        return ResultUtil.success(videoRepository.findAll());
    }
}
