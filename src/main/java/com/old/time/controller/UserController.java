package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.UserRepository;
import com.old.time.utils.GenerateShortUuid;
import com.old.time.utils.ResultUtil;
import com.old.time.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/user")
public class UserController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 添加（修改）用户
     *
     * @param userEntity
     * @return
     */
    @PostMapping(value = "/saveUser")
    public Result saveUser(UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();

        }
        if ("".equals(userEntity.getUserId())) {
            userEntity.setUserId(GenerateShortUuid.generateShortUuid());

        }
        return ResultUtil.success(userRepository.save(userEntity));
    }

    /**
     * 修改用户信息
     *
     * @param userEntity
     * @return
     */
    @PostMapping(value = "/updateUserMsg")
    public Result updateUserMsg(UserEntity userEntity) {
        if ("".equals(userEntity.getUserId())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        UserEntity mUserEntity = userRepository.findUserEntityByUserId(userEntity.getUserId());
        if (mUserEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        if (userEntity.getAvatar() != null) {
            mUserEntity.setAvatar(userEntity.getAvatar());

        }
        if (userEntity.getUserName() != null) {
            mUserEntity.setUserName(userEntity.getUserName());

        }
        if (userEntity.getBirthday() != null) {
            mUserEntity.setBirthday(userEntity.getBirthday());

        }
        if (userEntity.getVocation() != null) {
            mUserEntity.setVocation(userEntity.getVocation());

        }
        if (userEntity.getSex() != null) {
            mUserEntity.setSex(userEntity.getSex());

        }

        return ResultUtil.success(userRepository.save(mUserEntity));
    }

    /**
     * 通过userId获取用户
     *
     * @param userId
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/getUserByUserId")
    public Result getUserByUserId(@RequestParam("userId") String userId) throws RuntimeException {
        TimeUtil.inputFile();
        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }

        return ResultUtil.success(userEntity);
    }

    /**
     * 通过手机号查找用户
     *
     * @param mobile
     * @return
     */
    @PostMapping(value = "/getUserByMobil")
    public Result getUserByMobil(@RequestParam("mobile") String mobile) throws RuntimeException {
        UserEntity userEntity = userRepository.findByMobile(mobile);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }

        return ResultUtil.success(userEntity);
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @PostMapping(value = "/getUserList")
    @Override
    public Result getControllerList() {

        return ResultUtil.success(userRepository.findAll());
    }
}
