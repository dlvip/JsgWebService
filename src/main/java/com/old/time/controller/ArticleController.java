package com.old.time.controller;

import com.old.time.domain.Result;
import com.old.time.repository.ArticleRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result getControllerList() {
        return ResultUtil.success(articleRepository.findAll());
    }
}
