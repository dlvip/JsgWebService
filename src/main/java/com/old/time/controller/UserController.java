package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.UserRepository;
import com.old.time.utils.GenerateShortUuid;
import com.old.time.utils.ResultUtil;
import com.old.time.utils.StringUtils;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
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
            userEntity.setUserId(GenerateShortUuid.getPhoneUserId(userEntity.getMobile()));

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
        userEntity = userRepository.save(mUserEntity);

        return ResultUtil.success(setRongToken(userEntity));
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
        UserEntity userEntity = userRepository.findUserEntityByMobile(mobile);
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
    public Result getControllerList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {

        return ResultUtil.success(userRepository.findAll());
    }

    /**
     * 获取并存储融云token
     *
     * @param mobil
     * @return
     */
    @PostMapping(value = "/getUserRongToken")
    public Result getUserRongToken(@RequestParam("userId") String mobil) {
        UserEntity userEntity;
        boolean isExists = userRepository.existsByMobile(mobil);
        if (isExists) {
            userEntity = userRepository.findUserEntityByMobile(mobil);

        } else {
            userEntity = new UserEntity(GenerateShortUuid.getPhoneUserId(mobil), mobil, StringUtils.encodeByMd5("123456"));

        }
        return ResultUtil.success(setRongToken(userEntity));
    }

    /**
     * 设置用户融云token
     *
     * @param userEntity
     */
    private UserEntity setRongToken(UserEntity userEntity) {
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
