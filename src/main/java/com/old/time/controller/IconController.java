package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.repository.IconRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/icon")
public class IconController extends BaseController {

    @Autowired
    private IconRepository iconRepository;

    /**
     * 获取所有文章
     *
     * @return
     */
    @PostMapping(value = "/getIconList")
    @Override
    public Result getControllerList() {
        return ResultUtil.success(iconRepository.findAll());
    }
}
