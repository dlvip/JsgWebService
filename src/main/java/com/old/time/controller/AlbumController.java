package com.old.time.controller;

import com.old.time.domain.AlbumEntity;
import com.old.time.domain.Result;
import com.old.time.repository.AlbumRepository;
import com.old.time.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/album")
public class AlbumController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumRepository albumRepository;

    /**
     * 添加相册
     *
     * @param albumEntity
     * @return
     */
    @PostMapping(value = "/addAlbum")
    public Result addAlbum(AlbumEntity albumEntity) {

        return jSGuangService.saveAlbumEntity(albumEntity, albumRepository);
    }

    /**
     * 获取所有相册
     *
     * @return
     */
    @PostMapping(value = "/getAlbumList")
    @Override
    public Result getControllerList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return ResultUtil.success(albumRepository.findAll());
    }
}
