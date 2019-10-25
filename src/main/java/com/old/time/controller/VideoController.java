package com.old.time.controller;

import com.old.time.domain.*;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.EpisodeRepository;
import com.old.time.repository.TopicRepository;
import com.old.time.repository.TopicVideoBookRepository;
import com.old.time.repository.VideoRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private EpisodeRepository episodeRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TopicRepository topicRepository;

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
    @PostMapping(value = "/checkUpdate")
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
        if (!topicRepository.existsTopicEntityByTopic("#" + name)) {
            topicEntity = topicRepository.save(TopicEntity.getInstance("", "#" + name, pic));

        } else {
            topicEntity = topicRepository.findTopicEntityByTopic("#" + name);

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
     * 获取视频详情
     *
     * @param videoId
     * @return
     */
    @RequestMapping(value = "/getVideoDetail")
    public Result getVideoDetail(@RequestParam("videoId") Integer videoId) {
        VideoEntity videoEntity = videoRepository.findVideoEntityById(videoId);
        if (videoEntity != null) {
            videoEntity.setEpisodeEntities(episodeRepository.findEpisodeEntitiesByVideoId(videoEntity.getId()));

        }
        return ResultUtil.success(videoEntity);
    }

    /**
     * 名称搜索
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/searchVideo")
    public Result searchVideo(@RequestParam("name") String name) {
        List<VideoEntity> videoEntities = videoRepository.findAllByNameLike("%" + name + "%");

        return ResultUtil.success(videoEntities);
    }

    /**
     * 精选：00：重磅推荐、01：热门精选、02：科幻·动作、03：惊悚·剧情、04：小编·推荐
     * 男频：10：重磅推荐、11：热门精选、12：玄幻·奇幻、13：历史·军旅、14：游戏·竞技
     * 女频：20：重磅推荐、21：热门精选、22：现代·都市、23：灵异·穿越、24：美文·同人
     */
    /**
     * 获取精选
     *
     * @return
     */
    @RequestMapping(value = "/getRecommendVideo")
    public Result getRecommendVideo(@RequestParam("tabId") String aType) {
        List<ItemVideoEntity> itemVideoEntities = new ArrayList<>();
        switch (aType) {
            case "0"://推荐
                itemVideoEntities.add(ItemVideoEntity.getInstance("热门精选", videoRepository.findVideoEntitiesByWeight(1, PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("科幻·动作", videoRepository.findVideoEntitiesByWeight(2, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("惊悚·剧情", videoRepository.findVideoEntitiesByWeight(3, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("小编·推荐", videoRepository.findVideoEntitiesByWeight(4, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
            case "1"://男频
                itemVideoEntities.add(ItemVideoEntity.getInstance("重磅推荐", videoRepository.findVideoEntitiesByWeight(10, PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("热门精选", videoRepository.findVideoEntitiesByWeight(11, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("玄幻·奇幻", videoRepository.findVideoEntitiesByWeight(12, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("历史·军旅", videoRepository.findVideoEntitiesByWeight(13, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("游戏·竞技", videoRepository.findVideoEntitiesByWeight(14, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
            case "2"://女频
                itemVideoEntities.add(ItemVideoEntity.getInstance("重磅推荐", videoRepository.findVideoEntitiesByWeight(20, PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("热门精选", videoRepository.findVideoEntitiesByWeight(21, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("现代·都市", videoRepository.findVideoEntitiesByWeight(22, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("灵异·穿越", videoRepository.findVideoEntitiesByWeight(23, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemVideoEntities.add(ItemVideoEntity.getInstance("美文·同人", videoRepository.findVideoEntitiesByWeight(24, PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
        }

        return ResultUtil.success(itemVideoEntities);
    }

    @PostMapping(value = "/getVideoList")
    public Result getVideoList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<VideoEntity> videoEntities = videoRepository.findAll(PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"))).getContent();

        return ResultUtil.success(videoEntities);
    }

    @RequestMapping("/queryVideoList")
    public Result queryVideos(@RequestParam("type") String type, @RequestParam("country") String country, @RequestParam("createTime") String createTime, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<VideoEntity> videoEntities;
        if ("全部".equals(type) && "全部".equals(country) && "全部".equals(createTime)) {
            videoEntities = videoRepository.findAll(PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"))).getContent();

        } else if ("全部".equals(type) && "全部".equals(country) && !"全部".equals(createTime)) {
            videoEntities = videoRepository.findVideoEntitiesByCreateTimeLike(createTime, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else if ("全部".equals(type) && !"全部".equals(country) && "全部".equals(createTime)) {
            videoEntities = videoRepository.findVideoEntitiesByCountryLike(country, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else if (!"全部".equals(type) && "全部".equals(country) && "全部".equals(createTime)) {
            videoEntities = videoRepository.findVideoEntitiesByTypeLike(type, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else if (!"全部".equals(type) && !"全部".equals(country) && "全部".equals(createTime)) {
            videoEntities = videoRepository.findVideoEntitiesByTypeContainingAndCountryContaining(type, country, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else if (!"全部".equals(type) && "全部".equals(country) && !"全部".equals(createTime)) {
            videoEntities = videoRepository.findVideoEntitiesByTypeContainingAndCreateTimeContaining(type, createTime, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else if ("全部".equals(type) && !"全部".equals(country) && !"全部".equals(createTime)) {
            videoEntities = videoRepository.findVideoEntitiesByCountryContainingAndCreateTimeContaining(type, createTime, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else if (!"全部".equals(type) && !"全部".equals(country) && !"全部".equals(createTime)) {
            videoEntities = videoRepository.findVideoEntitiesByTypeContainingAndCountryContainingAndCreateTimeContaining(type, country, createTime, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else {
            videoEntities = videoRepository.findVideoEntitiesByTypeContainingAndCountryContainingAndCreateTimeContaining(type, country, createTime, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        }
        if (videoEntities == null) {
            videoEntities = new ArrayList<>();

        }
        return ResultUtil.success(videoEntities);
    }
}
