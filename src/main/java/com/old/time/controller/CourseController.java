package com.old.time.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 添加专辑
     *
     * @param mCourseEntity
     * @return
     */
    @PostMapping(value = "/addCourse")
    public Result addCourse(CourseEntity mCourseEntity) {

        return jSGuangService.saveCourseEntity(mCourseEntity, courseRepository);
    }

    /**
     * 添加专辑
     *
     * @param userId
     * @param albumId
     * @param title
     * @param coursePic
     * @return
     */
    @PostMapping(value = "/saveCourse")
    public Result saveCourse(@RequestParam("userId") String userId, @RequestParam("albumId") Integer albumId, @RequestParam("title") String title, @RequestParam("coursePic") String coursePic) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        CourseEntity courseEntity = new CourseEntity(userId, albumId, title, coursePic);

        return ResultUtil.success(courseRepository.save(courseEntity));
    }

    /**
     * 修改专辑信息
     *
     * @param courseEntity
     * @return
     */
    @PostMapping(value = "/updateCourse")
    public Result updateCourse(CourseEntity courseEntity) {
        if (courseEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        if (courseEntity.getUserId() == null || userRepository.existsByUserId(courseEntity.getUserId())) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        if (courseEntity.getAlbumId() == null || courseRepository.existsByAlbumId(courseEntity.getAlbumId())) {

            throw new JSGNoSuchElementException(ResultEnum.USER_COURSE_NON);
        }
        CourseEntity mCourseEntity = courseRepository.findByAlbumId(courseEntity.getAlbumId());
        if (mCourseEntity == null) {
//            mCourseEntity = courseEntity;//保存时候需要

            throw new JSGNoSuchElementException(ResultEnum.USER_COURSE_NON);
        }
        if (!courseEntity.getUserId().equals(mCourseEntity.getUserId())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_PERMISSION);
        }
        if (courseEntity.getCoursePic() != null) {
            mCourseEntity.setCoursePic(courseEntity.getCoursePic());

        }
        if (courseEntity.getTitle() != null) {
            mCourseEntity.setTitle(courseEntity.getTitle());

        }
        return ResultUtil.success(courseRepository.save(mCourseEntity));
    }

    /**
     * 获取专辑信息
     *
     * @param albumId
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/getCourse")
    public Result getCourse(@RequestParam("albumId") Integer albumId) throws RuntimeException {
        boolean isCourseExists = courseRepository.existsByAlbumId(albumId);
        if (!isCourseExists) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }

        return ResultUtil.success(courseRepository.findByAlbumId(albumId));
    }

    /**
     * 获取用户专辑列表（分页）
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/getUserCourseList")
    public Result getUserCourseList(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsByUserId(userId);
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
