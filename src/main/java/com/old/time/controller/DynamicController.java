package com.old.time.controller;

import com.old.time.domain.DynamicEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.DynamicRepository;
import com.old.time.repository.TopicRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/dynamic")
public class DynamicController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DynamicRepository dynamicRepository;

    @Autowired
    private TopicRepository topicRepository;

    /**
     * 创建动态
     *
     * @param userId
     * @param content
     * @param images
     * @param topicId
     * @return
     */
    @RequestMapping("/createDynamic")
    public Result createDynamic(@RequestParam("userId") String userId, @RequestParam("content") String content, @RequestParam("images") String images, @RequestParam("topicId") String topicId) {
        boolean isUser = userRepository.existsByUserId(userId);
        if (!isUser) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        DynamicEntity dynamicEntity = DynamicEntity.getInstance(userId, content, images, Integer.parseInt(topicId));
        dynamicEntity.setTopicEntity(topicRepository.findTopicEntityById(dynamicEntity.getTopicId()));
        dynamicEntity.setUserEntity(userRepository.findUserEntityByUserId(userId));
        return ResultUtil.success(dynamicRepository.save(dynamicEntity));
    }

    /**
     * 获取用户动态
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getUserDynamicEntities")
    public Result getUserDynamicEntities(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUser = userRepository.existsByUserId(userId);
        if (!isUser) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<DynamicEntity> dynamicEntities = dynamicRepository.findDynamicEntitiesByUserId(userId, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));
        for (DynamicEntity dynamicEntity : dynamicEntities) {
            dynamicEntity.setUserEntity(userRepository.findUserEntityByUserId(dynamicEntity.getUserId()));
            dynamicEntity.setTopicEntity(topicRepository.findTopicEntityById(dynamicEntity.getTopicId()));

        }

        return ResultUtil.success(dynamicEntities);
    }

    /**
     * 获取话题下的动态
     *
     * @param topicId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getTopicDynamicEntities")
    public Result getTopicDynamicEntities(@RequestParam("topicId") String topicId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isTopic = topicRepository.existsTopicEntityById(Integer.parseInt(topicId));
        if (!isTopic) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        List<DynamicEntity> dynamicEntities = dynamicRepository.findDynamicEntitiesByTopicId(Integer.parseInt(topicId), PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));
        for (DynamicEntity dynamicEntity : dynamicEntities) {
            dynamicEntity.setUserEntity(userRepository.findUserEntityByUserId(dynamicEntity.getUserId()));
            dynamicEntity.setTopicEntity(topicRepository.findTopicEntityById(dynamicEntity.getTopicId()));

        }

        return ResultUtil.success(dynamicEntities);
    }

    /**
     * 获取全部动态列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getDynamicEntities")
    public Result getDynamicEntities(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<DynamicEntity> dynamicEntities = dynamicRepository.findAll(PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"))).getContent();
        for (DynamicEntity dynamicEntity : dynamicEntities) {
            dynamicEntity.setUserEntity(userRepository.findUserEntityByUserId(dynamicEntity.getUserId()));
            dynamicEntity.setTopicEntity(topicRepository.findTopicEntityById(dynamicEntity.getTopicId()));

        }

        return ResultUtil.success(dynamicEntities);
    }

    /**
     * 获取动态详情
     *
     * @param dynamicId
     * @return
     */
    @RequestMapping("/getDynamicDetail")
    public Result getDynamicDetail(@RequestParam("dynamicId") String dynamicId) {
        boolean isExists = dynamicRepository.existsDynamicEntityById(Integer.parseInt(dynamicId));
        if (!isExists) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(dynamicRepository.findDynamicEntityById(Integer.parseInt(dynamicId)));
    }

    /**
     * 删除动态
     *
     * @param userId
     * @param dynamicId
     * @return
     */
    @RequestMapping("/deleteDynamicEntity")
    public Result deleteDynamicEntity(@RequestParam("userId") String userId, @RequestParam("dynamicId") String dynamicId) {
        boolean isUser = userRepository.existsByUserId(userId);
        if (!isUser) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isExists = dynamicRepository.existsDynamicEntityById(Integer.parseInt(dynamicId));
        if (!isExists) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(dynamicRepository.deleteDistinctByUserIdAndId(userId, Integer.parseInt(dynamicId)));
    }
}
