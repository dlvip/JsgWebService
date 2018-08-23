package com.old.time.controller;

import com.old.time.domain.MusicEntry;
import com.old.time.domain.Result;
import com.old.time.repository.MusicRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/music")
public class MusicController extends BaseController {

    @Autowired
    private MusicRepository musicRepository;

    @PostMapping(value = "/addMusic")
    public Result addCourse(MusicEntry musicEntry) {

        return jSGuangService.saveMusicEntity(musicEntry, musicRepository);
    }

    @PostMapping(value = "/getMusicList")
    @Override
    public Result getControllerList() {
        return ResultUtil.success(musicRepository.findAll());
    }

    @PostMapping(value = "/findMusicEntriesByAlbumId")
    public Result getMusicListById(@RequestParam("albumId") Integer albumId) {
        return ResultUtil.success(musicRepository.findMusicEntriesByAlbumId(albumId, PageRequest.of(1, 500)));
    }
}
