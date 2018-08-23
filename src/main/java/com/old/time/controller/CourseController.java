package com.old.time.controller;

import com.old.time.domain.CourseEntity;
import com.old.time.domain.Result;
import com.old.time.repository.CourseRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/course")
public class CourseController extends BaseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping(value = "/addCourse")
    public Result addCourse(CourseEntity mCourseEntity) {

        return jSGuangService.saveCourseEntity(mCourseEntity, courseRepository);
    }

    @PostMapping(value = "/getCourseList")
    @Override
    public Result getControllerList() {
        return ResultUtil.success(courseRepository.findAll());
    }
}
