package com.old.time.controller;

import com.old.time.domain.Result;
import com.old.time.repository.BannerRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/banner")
public class BannerController extends BaseController {

    @Autowired
    private BannerRepository bannerRepository;

    /**
     * 获取所有文章
     *
     * @return
     */
    @PostMapping(value = "/getBanners")
    @Override
    public Result getControllerList() {

        return ResultUtil.success(bannerRepository.findAll());
    }
}
