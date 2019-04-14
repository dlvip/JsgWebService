package com.old.time.controller;

import com.old.time.domain.EpisodeEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.EpisodeRepository;
import com.old.time.repository.VideoRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/episode")
public class EpisodeController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @PostMapping(value = "/createEpisode")
    public Result createEpisode(@RequestParam("videoId") int videoId, @RequestParam("url") String url) {
        if (!videoRepository.existsById(videoId)) {

            throw new JSGRuntimeException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(episodeRepository.save(EpisodeEntity.instance(videoId, url)));
    }

    @PostMapping(value = "/getEpisodeList")
    public Result getEpisodeList(@RequestParam("videoId") int videoId) {
        List<EpisodeEntity> episodeEntities = episodeRepository.findEpisodeEntitiesByVideoId(videoId);
        if (episodeEntities == null) {
            episodeEntities = new ArrayList<>();

        }
        return ResultUtil.success(episodeEntities);
    }

}
