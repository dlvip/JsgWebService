package com.old.time.repository;

import com.old.time.domain.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    /**
     * 获取
     *
     * @param topicId
     * @return
     */
    List<CommentEntity> findCommentEntitiesByTopicId(Integer topicId, Pageable pageable);

    /**
     * 获取评论数量
     *
     * @param topicId
     * @return
     */
    int countByTopicId(Integer topicId);

    /**
     * 删除评论
     *
     * @param userId
     * @param commentId
     * @return
     */
    boolean deleteCommentEntityByUserIdAndTopicIdAndCommentId(String userId, Integer topicId, Integer commentId);

}
