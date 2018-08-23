package com.old.time.controller;

import com.old.time.domain.CourseEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.CourseRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/course")
public class CourseController extends BaseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/addCourse")
    public Result addCourse(CourseEntity mCourseEntity) {

        return jSGuangService.saveCourseEntity(mCourseEntity, courseRepository);
    }

    /**
     * 获取课程信息
     *
     * @param albumId
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/getCourse")
    public Result getCourse(@RequestParam("albumId") Integer albumId) throws RuntimeException {
        boolean isCourseExists = courseRepository.existsById(albumId);
        if (!isCourseExists) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }

        return ResultUtil.success(courseRepository.findByAlbumId(albumId));
    }

    /**
     * 获取专辑列表（分页）
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/getUserCourseList")
    public Result getUserCourseList(@RequestParam("userId") Integer userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsById(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }

        List<CourseEntity> courseEntityList = courseRepository.findCourseEntitiesByUserId(userId, PageRequest.of(pageNum, pageSize));

        return ResultUtil.success(courseEntityList);
    }

    /**
     * 获取专辑列表
     *
     * @return
     */
    @PostMapping(value = "/getCourseList")
    public Result getCourseList() {

        List<CourseEntity> courseEntityList = courseRepository.findAll();

        return ResultUtil.success(courseEntityList);
    }
}
