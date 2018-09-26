package com.old.time.controller;

import com.old.time.domain.CommentEntity;
import com.old.time.domain.Result;
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
@RequestMapping(value = "jiushiguang/comment")
public class CommentController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 添加评论
     *
     * @param userId
     * @param topicId
     * @param comment
     * @return
     */
    @PostMapping(value = "/insertComment")
    public Result insertCommentEntity(@RequestParam("userId") String userId, @RequestParam("topicId") Integer topicId, @RequestParam("comment") String comment) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isExists = topicRepository.existsByTopicId(topicId);
        if (!isExists) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        CommentEntity commentEntity = new CommentEntity(userId, topicId, comment);

        return ResultUtil.success(commentRepository.save(commentEntity));
    }

    /**
     * 获取评论列表
     *
     * @param topicId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/getCommentList")
    public Result getCommentList(@RequestParam("topicId") Integer topicId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isExists = topicRepository.existsByTopicId(topicId);
        if (!isExists) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        List<CommentEntity> commentEntities = commentRepository.findCommentEntitiesByTopicId(topicId, PageRequest.of(pageNum, pageSize));
        if (commentEntities == null) {

            return ResultUtil.success(new ArrayList<CommentEntity>());
        }
        for (CommentEntity commentEntity : commentEntities) {
            commentEntity.setUserEntity(userRepository.findByUserId(commentEntity.getUserId()));

        }
        return ResultUtil.success(commentEntities);
    }

    /**
     * 删除评论
     *
     * @param userId
     * @param topicId
     * @param commentId
     * @return
     */
    @PostMapping(value = "/deleteComment")
    public Result deleteComment(@RequestParam("userId") String userId, @RequestParam("topicId") Integer topicId, @RequestParam("commentId") Integer commentId) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isExists = topicRepository.existsByTopicId(topicId);
        if (!isExists) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(commentRepository.deleteCommentEntityByUserIdAndTopicIdAndCommentId(userId, topicId, commentId));
    }
}
