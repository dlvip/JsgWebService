package com.old.time.controller;

import com.old.time.domain.MsgCodeEntity;
import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.MsgCodeRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.MsgCodeUtils;
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
@RequestMapping(value = "jiushiguang")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MsgCodeRepository msgCodeRepository;

    /**
     * 获取验证码
     *
     * @param mobile
     */
    @PostMapping(value = "/getMobileCode")
    public Result getMobileCode(@RequestParam("mobile") String mobile) {
        MsgCodeEntity msgCodeEntity;
        boolean isExists = msgCodeRepository.existsByMobile(mobile);
        if (isExists) {
            msgCodeEntity = msgCodeRepository.findByMobile(mobile);
            long currentTimeMillis = System.currentTimeMillis();
            long endTime = msgCodeEntity.getEndTime();
            if (currentTimeMillis < endTime) {

                throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_SEND);
            }
            msgCodeEntity.setMobile(mobile);
            msgCodeEntity.setCode(MsgCodeUtils.getMsgCode(mobile));
            msgCodeEntity.setCreateTime(currentTimeMillis);
            msgCodeEntity.setEndTime((currentTimeMillis + 2 * 60 * 1000));

        } else {
            msgCodeEntity = MsgCodeEntity.getInstance(mobile, MsgCodeUtils.getMsgCode(mobile));

        }

        msgCodeRepository.save(msgCodeEntity);
        return ResultUtil.success();
    }

    /**
     * 用户注册
     *
     * @param mobile
     * @param pasWord
     * @param code
     * @return
     */
    @PostMapping(value = "/registerUser")
    public Result registerUser(@RequestParam("mobile") String mobile, @RequestParam("pasWord") String pasWord, @RequestParam("code") String code) throws RuntimeException {
        boolean isUserExist = userRepository.existsByMobile(mobile);
        if (isUserExist) {//手机号是否已经被注册

            throw new JSGNoSuchElementException(ResultEnum.USER_ALREADY_EXISTENT);
        }
        boolean isCodeExists = msgCodeRepository.existsByMobile(mobile);
        if (!isCodeExists) {//验证码是否存在

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }
        MsgCodeEntity msgCodeEntity = msgCodeRepository.findByMobile(mobile);
        long currentTime = System.currentTimeMillis();
        long endTime = msgCodeEntity.getEndTime();
        if (currentTime > endTime) {//验证码是否过期

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }
        if (!code.equals(msgCodeEntity.getCode())) {//验证码是否正确

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_ERROR);
        }
        UserEntity userEntity = UserEntity.getInstance(mobile, pasWord);
        return ResultUtil.success(setRongToken(userEntity));
    }

    @PostMapping(value = "/registerSystemUser")
    public Result registerSystemUser(@RequestParam("avatar") String avatar, @RequestParam("userName") String userName
            , @RequestParam("userId") String userId, @RequestParam("mobile") String mobile) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAvatar(avatar);
        userEntity.setUserName(userName);
        userEntity.setPasWord(StringUtils.encodeByMd5("123456"));
        userEntity.setUserId(userId);
        userEntity.setMobile(mobile);
        userEntity.setToken(mobile);

        return ResultUtil.success(userRepository.save(userEntity));
    }

    /**
     * 密码登陆
     *
     * @param mobile
     * @param pasWord
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/loginUser")
    public Result loginUser(@RequestParam("mobile") String mobile, @RequestParam("pasWord") String pasWord) {
        boolean isUser = userRepository.existsByMobile(mobile);
        if (!isUser) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isExists = userRepository.existsByMobileAndPasWord(mobile, pasWord);
        if (!isExists) {

            throw new JSGRuntimeException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        UserEntity userEntity = userRepository.findByMobileAndPasWord(mobile, pasWord);
        return ResultUtil.success(setRongToken(userEntity));
    }

    /**
     * 验证码登陆
     *
     * @param mobile
     * @param code
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/loginMobilCode")
    public Result loginMobilCode(@RequestParam("mobile") String mobile, @RequestParam("code") String code) {
        boolean isUser = userRepository.existsByMobile(mobile);
        if (!isUser) {//用户是否存在

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isCodeExists = msgCodeRepository.existsByMobile(mobile);
        if (!isCodeExists) {//验证码是否存在

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }

        MsgCodeEntity msgCodeEntity = msgCodeRepository.findByMobile(mobile);
        long currentTime = System.currentTimeMillis();
        long endTime = msgCodeEntity.getEndTime();
        if (currentTime > endTime) {//验证码是否过期

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }
        if (!code.equals(msgCodeEntity.getCode())) {//验证码是否正确

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_ERROR);
        }
        UserEntity userEntity = userRepository.findUserEntityByMobile(mobile);
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

    /**
     * 修改密码
     *
     * @param userId
     * @param pasWord
     * @param newPasWord
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/updatePasWord")
    public Result updatePasWord(@RequestParam("userId") String userId, @RequestParam("pasWord") String pasWord, @RequestParam("newPasWord") String newPasWord) {
        boolean isUserExists = userRepository.existsByUserIdAndPasWord(userId, pasWord);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        UserEntity userEntity = userRepository.findByUserIdAndPasWord(userId, pasWord);
        userEntity.setPasWord(newPasWord);
        return ResultUtil.success(userRepository.save(userEntity));
    }

    /**
     * 设置新密码
     *
     * @param mobile
     * @param code
     * @param pasWord
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/forgetPasWord")
    public Result forgetPasWord(@RequestParam("mobile") String mobile, @RequestParam("code") String code, @RequestParam("pasWord") String pasWord) throws RuntimeException {
        boolean isUser = userRepository.existsByMobile(mobile);
        if (!isUser) {//用户是否存在

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isCodeExists = msgCodeRepository.existsByMobile(mobile);
        if (!isCodeExists) {//验证码是否存在

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }
        MsgCodeEntity msgCodeEntity = msgCodeRepository.findByMobile(mobile);
        long currentTime = System.currentTimeMillis();
        long endTime = msgCodeEntity.getEndTime();
        if (currentTime > endTime) {//验证码是否过期

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }
        if (!code.equals(msgCodeEntity.getCode())) {//验证码是否正确

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_ERROR);
        }
        UserEntity userEntity = userRepository.findUserEntityByMobile(mobile);
        userEntity.setPasWord(pasWord);
        return ResultUtil.success(userRepository.save(userEntity));
    }
}
