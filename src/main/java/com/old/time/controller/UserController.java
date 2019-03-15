package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.UserRepository;
import com.old.time.utils.GenerateShortUuid;
import com.old.time.utils.ResultUtil;
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

    @PostMapping(value = "/getUserRongToken")
    public Result getUserRongToken(@RequestParam("userId") String mobil) {
        boolean isExists = userRepository.existsByMobile(mobil);
        UserEntity userEntity;
        if (isExists) {
            userEntity = userRepository.findUserEntityByMobile(mobil);

        } else {
            userEntity = new UserEntity(GenerateShortUuid.getPhoneUserId(mobil), mobil, "123456");

        }
        if (userEntity.getToken() == null) {
            try {
                String appKey = "x18ywvqfxcbjc";//融云key
                String appSecret = "pzfndTCPu9";//融云秘钥
                RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
                User user = rongCloud.user;
                UserModel userModel = new UserModel()
                        .setId(userEntity.getUserId())
                        .setName(userEntity.getMobile())
                        .setPortrait(userEntity.getAvatar());
                TokenResult result = user.register(userModel);
                userEntity.setToken(result.getToken());

                userEntity = userRepository.save(userEntity);
            } catch (Exception e) {
                e.printStackTrace();
                throw new JSGRuntimeException(ResultEnum.SYSTEM_ERROR);

            }
        }

        return ResultUtil.success(userEntity);
    }
}
