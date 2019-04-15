package com.old.time.controller;

import com.old.time.domain.Result;
import com.old.time.domain.TopicVideoBookEntry;
import com.old.time.domain.VideoEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.EpisodeRepository;
import com.old.time.repository.TopicVideoBookRepository;
import com.old.time.repository.VideoRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/topicVideoBook")
public class TopicVideoBookController {

    @Autowired
    private TopicVideoBookRepository topicVideoBookRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    /**
     * 获取话题id
     *
     * @param videoId
     * @return
     */
    @PostMapping(value = "/getTopicId")
    public Result getTopicId(@RequestParam("videoId") int videoId) {
        TopicVideoBookEntry topicVideoBookEntry = getTopicVideoBookEntry(1, videoId);
        if (topicVideoBookEntry == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(topicVideoBookEntry.getTopicId());
    }


    /**
     * 获取视频信息
     *
     * @param type    0:话题id，1：视频id，2：图书id
     * @param videoId
     * @return
     */
    @PostMapping(value = "/getVideoDetail")
    public Result getVideoDetail(@RequestParam("type") int type, @RequestParam("videoId") int videoId) {
        TopicVideoBookEntry topicVideoBookEntry = getTopicVideoBookEntry(type, videoId);
        if (topicVideoBookEntry == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        VideoEntity videoEntity = videoRepository.findVideoEntityById(topicVideoBookEntry.getVideoId());
        if (videoEntity == null) {

            throw new JSGRuntimeException(ResultEnum.NULL_DATA_ERROR);
        }
        videoEntity.setEpisodeEntities(episodeRepository.findEpisodeEntitiesByVideoId(topicVideoBookEntry.getVideoId()));
        return ResultUtil.success(videoEntity);
    }


    /**
     * 获取实例
     *
     * @param type  0:话题，1：视频，2：图书
     * @param typeId
     * @return
     */
    private TopicVideoBookEntry getTopicVideoBookEntry(int type, int typeId) {
        TopicVideoBookEntry topicVideoBookEntry;
        switch (type) {
            case 0:
                topicVideoBookEntry = topicVideoBookRepository.findTopicVideoBookEntryByTopicId(typeId);

                break;
            case 1:
                topicVideoBookEntry = topicVideoBookRepository.findTopicVideoBookEntryByVideoId(typeId);

                break;
            default:
                topicVideoBookEntry = topicVideoBookRepository.findTopicVideoBookEntryByBookId(typeId);

                break;
        }

        return topicVideoBookEntry;
    }
}
