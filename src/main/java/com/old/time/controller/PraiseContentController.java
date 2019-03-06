package com.old.time.controller;

import com.old.time.domain.PraiseEntity;
import com.old.time.domain.Result;
import com.old.time.repository.PraiseContentRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/praise")
public class PraiseContentController extends BaseController {

    @Autowired
    private PraiseContentRepository praiseContentRepository;

    /**
     * 添加打卡点赞
     *
     * @param userId
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/createSignPraise")
    public Result createSignPraiseContent(@RequestParam("userId") String userId, @RequestParam("contentId") Integer contentId) {
        PraiseEntity praiseEntity = praiseContentRepository.findPraiseEntityByPraiseTypeAndUserIdAndContentId(PraiseEntity.PRAISE_TYPE_0, userId, contentId);
        if (praiseEntity == null) {
            praiseEntity = PraiseEntity.getInstance(userId, PraiseEntity.PRAISE_TYPE_0, contentId);
            praiseContentRepository.save(praiseEntity);

        } else {
            praiseContentRepository.delete(praiseEntity);

        }

        return ResultUtil.success();
    }

    /**
     * 用户是否点过赞
     *
     * @param userId
     * @param contentId
     * @return
     */
    public boolean isPraiseUser(String userId, Integer contentId) {

        return praiseContentRepository.existsPraiseEntityByPraiseTypeAndUserIdAndContentId(PraiseEntity.PRAISE_TYPE_0, userId, contentId);
    }

    /**
     * 获取赞数量
     *
     * @param contentId
     * @return
     */
    int getPraiseCount(Integer contentId) {

        return praiseContentRepository.countAllByContentId(contentId);
    }
}
