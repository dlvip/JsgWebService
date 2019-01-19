package com.old.time.controller;

import com.old.time.domain.ArticleEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.ArticleRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "jiushiguang/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 获取所有文章
     *
     * @return
     */
    @PostMapping(value = "/getArticleList")
    @Override
    public Result getControllerList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<ArticleEntity> articleEntities = articleRepository.findAll(PageRequest.of(pageNum, pageSize)).getContent();

        return ResultUtil.success(articleEntities);
    }

    /**
     * 保存文章
     *
     * @param picUrl
     * @param title
     * @param detailUrl
     * @return
     */
    @PostMapping(value = "/saveArticle")
    public Result saveArticle(@RequestParam("picUrl") String picUrl, @RequestParam("title") String title, @RequestParam("detailUrl") String detailUrl) {
        ArticleEntity articleEntity = new ArticleEntity(picUrl, title, detailUrl, new Random(200).nextInt() + 189);

        return ResultUtil.success(articleRepository.save(articleEntity));
    }

    /**
     * 修改文章阅读数量
     *
     * @return
     */
    @PostMapping(value = "/updateArticleReadCount")
    public Result updateArticleReadCount(@RequestParam("articleId") Integer articleId) {
        boolean isExist = articleRepository.existsById(articleId);
        if (!isExist) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }
        ArticleEntity articleEntity = articleRepository.findArticleEntityById(articleId);
        articleEntity.setLookCount(articleEntity.getLookCount() + 1);
        articleRepository.save(articleEntity);

        return ResultUtil.success();
    }
}
