package com.old.time.service;

import com.old.time.domain.*;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.AlbumRepository;
import com.old.time.repository.CourseRepository;
import com.old.time.repository.MusicRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import com.old.time.utils.StringUtils;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JSGuangService {

    @Transactional
    public void insertTwo() {

    }

    /**
     * 保存相册
     *
     * @param albumEntity
     * @return
     * @throws RuntimeException
     */
    public Result saveAlbumEntity(AlbumEntity albumEntity, AlbumRepository albumRepository) throws RuntimeException {
        if (albumEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }
        return ResultUtil.success(albumRepository.save(albumEntity));
    }

    /**
     * 添加课程
     *
     * @param mCourseEntity
     * @param courseRepository
     * @return
     */
    public Result saveCourseEntity(CourseEntity mCourseEntity, CourseRepository courseRepository) throws RuntimeException {
        if (mCourseEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(courseRepository.save(mCourseEntity));
    }

    /**
     * 添加章节
     *
     * @param musicEntry
     * @param musicRepository
     * @return
     * @throws RuntimeException
     */
    public Result saveMusicEntity(MusicEntry musicEntry, MusicRepository musicRepository) throws RuntimeException {
        if (musicEntry == null) {

            throw new JSGNoSuchElementException(ResultEnum.NULL_DATA_ERROR);
        }

        return ResultUtil.success(musicRepository.save(musicEntry));
    }

    @Autowired
    private UserRepository userRepository;

    /**
     * 设置用户融云token
     *
     * @param userEntity
     */
    public UserEntity setRongToken(UserEntity userEntity) {
        try {
            RongCloud rongCloud = RongCloud.getInstance(StringUtils.RONG_APP_KEY, StringUtils.RONG_APP_SECRET);
            User user = rongCloud.user;
            UserModel userModel = new UserModel()
                    .setId(userEntity.getUserId())
                    .setName(userEntity.getMobile())
                    .setPortrait(userEntity.getAvatar());
            if (userEntity.getToken() == null || "".equals(userEntity.getToken())) {
                TokenResult result = user.register(userModel);
                userEntity.setToken(result.getToken());
                userEntity = userRepository.save(userEntity);

            } else {
                user.update(userModel);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JSGRuntimeException(ResultEnum.SYSTEM_ERROR);

        }
        return userEntity;
    }
}
