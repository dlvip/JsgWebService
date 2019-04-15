package com.old.time.controller;

import com.old.time.domain.Result;
import com.old.time.domain.TopicEntity;
import com.old.time.domain.TopicVideoBookEntry;
import com.old.time.domain.VideoEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.EpisodeRepository;
import com.old.time.repository.TopicRepository;
import com.old.time.repository.TopicVideoBookRepository;
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
@RequestMapping(value = "jiushiguang/video")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private TopicVideoBookRepository topicVideoBookRepository;

    /**
     * 创建电影
     *
     * @param name
     * @param detail
     * @param pic
     * @param country
     * @param film
     * @param type
     * @param score
     * @param episodes
     * @param totalEpisodes
     * @return
     */
    @PostMapping(value = "/createVideoEntity")
    public Result createVideoEntity(@RequestParam("name") String name
            , @RequestParam("detail") String detail
            , @RequestParam("pic") String pic
            , @RequestParam("country") String country
            , @RequestParam("film") String film
            , @RequestParam("type") String type
            , @RequestParam("score") String score
            , @RequestParam("episodes") int episodes
            , @RequestParam("totalEpisodes") int totalEpisodes) {

        if (videoRepository.existsVideoEntityByName(name)) {

            throw new JSGRuntimeException(ResultEnum.DATA_ALREADY_ERROR);
        }
        VideoEntity videoEntity = videoRepository.save(VideoEntity.instance(name
                , detail
                , pic
                , country
                , film
                , type
                , score
                , episodes
                , totalEpisodes));
        TopicEntity topicEntity;
        if (!topicRepository.existsTopicEntityByTopic(name)) {
            topicEntity = topicRepository.save(TopicEntity.getInstance("", name, pic));

        } else {
            topicEntity = topicRepository.findTopicEntityByTopic(name);

        }
        TopicVideoBookEntry topicVideoBookEntry = topicVideoBookRepository.findTopicVideoBookEntryByTopicId(topicEntity.getId());
        if (topicVideoBookEntry == null) {
            topicVideoBookEntry = TopicVideoBookEntry.instance(videoEntity.getId(), topicEntity.getId(), -1);

        } else {
            topicVideoBookEntry.setVideoId(videoEntity.getId());

        }
        topicVideoBookRepository.save(topicVideoBookEntry);

        return ResultUtil.success(videoEntity);
    }

    /**
     * 获取视频信息
     *
     * @param videoId
     * @return
     */
    @PostMapping(value = "/getVideoDetail")
    public Result getVideoDetail(@RequestParam("videoId") int videoId) {
        VideoEntity videoEntity = videoRepository.findVideoEntityById(videoId);
        if (videoEntity == null) {

            throw new JSGRuntimeException(ResultEnum.NULL_DATA_ERROR);
        }
        videoEntity.setEpisodeEntities(episodeRepository.findEpisodeEntitiesByVideoId(videoId));
        return ResultUtil.success(videoEntity);
    }

    /**
     * 查询列表
     *
     * @param type 类型 0:推荐 1:地区（中国/香港）、2：剧情类别（惊悚/悬疑/魔幻）、3：视频类型（电影/电视/动漫）
     * @param name 关键字
     * @return
     */
    @PostMapping(value = "/getVideoList")
    public Result getVideoList(@RequestParam("type") int type, @RequestParam("name") String name) {
        List<VideoEntity> videoEntities;
        switch (type) {
            case 0:
                videoEntities = videoRepository.findDistinctFirstByWeight(Integer.parseInt(name));

                break;
            case 1:
                videoEntities = videoRepository.findVideoEntitiesByCountry(name);

                break;
            case 2:
                videoEntities = videoRepository.findVideoEntitiesByType(name);

                break;
            case 3:
                videoEntities = videoRepository.findVideoEntitiesByFilm(name);

                break;
            default:
                videoEntities = new ArrayList<>();

                break;
        }

        return ResultUtil.success(videoEntities);
    }
}
