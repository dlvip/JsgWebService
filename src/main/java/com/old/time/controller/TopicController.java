package com.old.time.controller;

import com.old.time.domain.CommentEntity;
import com.old.time.domain.Result;
import com.old.time.domain.TopicEntity;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.CommentRepository;
import com.old.time.repository.TopicRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 创建话题
     *
     * @param userId       用户id
     * @param topicTitle   问题
     * @param topicContent 描述
     * @return
     */
    @PostMapping(value = "/insertTopic")
    public Result insertTopicEntity(@RequestParam("userId") String userId, @RequestParam("topicTitle") String topicTitle, @RequestParam("topicContent") String topicContent) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        TopicEntity topicEntity = new TopicEntity(userId, topicTitle, topicContent);

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
        boolean isExists = topicRepository.existsByTopicId(topicId);
        if (!isExists) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(topicRepository.findTopicEntitiesByTopicId(topicId));
    }

    /**
     * 获取用户话题列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/getTopicList")
    public Result getTopicList(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<TopicEntity> topicEntities = topicRepository.findTopicEntitiesByUserId(userId, PageRequest.of(pageNum, pageSize));
        if (topicEntities == null) {

            return ResultUtil.success(new ArrayList<CommentEntity>());
        }
        for (TopicEntity topicEntity : topicEntities) {
            topicEntity.setCommentCount(commentRepository.countByTopicId(topicEntity.getTopicId()));

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
        boolean isDelete = topicRepository.deleteTopicEntityByUserIdAndTopicId(userId, topicId);
        if (!isDelete) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }

        return ResultUtil.success();
    }
}
