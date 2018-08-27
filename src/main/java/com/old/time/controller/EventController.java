package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.repository.EventRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/event")
public class EventController extends BaseController {

    @Autowired
    private EventRepository eventRepository;

    /**
     * 获取所有文章
     *
     * @return
     */
    @PostMapping(value = "/getEventList")
    @Override
    public Result getControllerList() {
        return ResultUtil.success(eventRepository.findAll());
    }
}
