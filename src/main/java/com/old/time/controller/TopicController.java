package com.old.time.controller;

import com.old.time.domain.Result;
import com.old.time.domain.TopicEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.TopicRepository;
import com.old.time.repository.UserRepository;
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
@RequestMapping(value = "jiushiguang/topic")
public class TopicController extends BaseController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 创建话题
     *
     * @param userId 用户id
     * @param topic  话题名称
     * @param pic    图片地址
     * @return
     */
    @PostMapping(value = "/createTopicEntity")
    public Result createTopicEntity(@RequestParam("userId") String userId, @RequestParam("topic") String topic, @RequestParam("pic") String pic) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        TopicEntity topicEntity = TopicEntity.getInstance(userId, topic, pic);

        return ResultUtil.success(topicRepository.save(topicEntity));
    }

    /**
     * 获取话题详情
     *
     * @param topicId
     * @return
     */
    @PostMapping(value = "/getTopicDetail")
    public Result getTopicDetail(@RequestParam("topicId") Integer topicId) {
        boolean isExists = topicRepository.existsTopicEntityById(topicId);
        if (!isExists) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(topicRepository.findTopicEntityById(topicId));
    }

    /**
     * 获取话题列表分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/getTopicList")
    public Result getTopicList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<TopicEntity> topicEntities = topicRepository.findAll(PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"))).getContent();

        return ResultUtil.success(topicEntities);
    }

    /**
     * 获取用户话题列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/getUserTopicList")
    public Result getUserTopicList(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<TopicEntity> topicEntities = topicRepository.findTopicEntitiesByUserId(userId, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));
        if (topicEntities == null) {

            return ResultUtil.success(new ArrayList<TopicEntity>());
        }
        return ResultUtil.success(topicEntities);
    }

    /**
     * 删除话题
     *
     * @param userId
     * @param topicId
     * @return
     */
    @PostMapping(value = "/deleteTopic")
    public Result deleteTopicByTopicId(String userId, Integer topicId) {
        boolean isDelete = topicRepository.deleteTopicEntityByUserIdAndId(userId, topicId);
        if (!isDelete) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }

        return ResultUtil.success();
    }
}
