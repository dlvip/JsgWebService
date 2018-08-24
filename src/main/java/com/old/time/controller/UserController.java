package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.UserRepository;
import com.old.time.utils.GenerateShortUuid;
import com.old.time.utils.ResultUtil;
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
     * @param userId   用户id
     * @param userName 用户名称
     * @param avatar   用户头像
     * @param birthday 生日
     * @param sex      性别
     * @return
     */
    @PostMapping(value = "/updateUserMsg")
    public Result updateUserMsg(@RequestParam("userId") String userId, @RequestParam("userName") String userName, @RequestParam("avatar") String avatar, @RequestParam("birthday") String birthday, @RequestParam("sex") int sex) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        userEntity.setAvatar(avatar);
        userEntity.setBirthday(birthday);
        userEntity.setUserName(userName);
        userEntity.setSex(sex);

        return ResultUtil.success(userRepository.save(userEntity));
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
        UserEntity userEntity = userRepository.findByUserId(userId);
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
