package com.old.time.controller;

import com.old.time.domain.DownLoadEntity;
import com.old.time.domain.Result;
import com.old.time.repository.DownLoadRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang")
public class DownLoadController extends BaseController {

    @Autowired
    private DownLoadRepository downLoadRepository;

    @RequestMapping(value = "/checkSystemForce")
    public Result checkSystemForce() {
        Pageable pageable = PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "id"));
        List<DownLoadEntity> bookEntities = downLoadRepository.findAll(pageable).getContent();
        DownLoadEntity downLoadEntity = null;
        if (bookEntities.size() > 0) {
            downLoadEntity = bookEntities.get(0);

        }
        return ResultUtil.success(downLoadEntity);

    }
}
