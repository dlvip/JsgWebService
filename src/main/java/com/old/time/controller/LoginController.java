package com.old.time.controller;

import com.old.time.aspect.HttpAspect;
import com.old.time.domain.MsgCodeEntity;
import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.MsgCodeRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.GenerateShortUuid;
import com.old.time.utils.MsgCodeUtils;
import com.old.time.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang")
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

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
        long currentTimeMillis = System.currentTimeMillis();
        MsgCodeEntity msgCodeEntity = msgCodeRepository.findByMobile(mobile);
        if (msgCodeEntity != null && currentTimeMillis < msgCodeEntity.getEndTime()) {

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_SEND);
        }
        String code = MsgCodeUtils.getMsgCode(mobile);
        if(msgCodeEntity == null){
            msgCodeEntity = new MsgCodeEntity();

        }
        msgCodeEntity.setMobile(mobile);
        msgCodeEntity.setCode(code);
        msgCodeEntity.setEndTime((currentTimeMillis + 2 * 60 * 1000));
        msgCodeEntity.setCreateTime(currentTimeMillis);
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
        UserEntity userEntity = userRepository.findByMobile(mobile);
        if (userEntity != null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_ALREADY_EXISTENT);
        }

        MsgCodeEntity msgCodeEntity = msgCodeRepository.findByMobile(mobile);
        if (msgCodeEntity == null || !code.equals(msgCodeEntity.getCode())) {

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_ERROR);
        }
        if (System.currentTimeMillis() > msgCodeEntity.getEndTime()) {

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }
        userEntity = new UserEntity(GenerateShortUuid.generateShortUuid(), mobile, pasWord);

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
    public Result loginUser(@RequestParam("mobile") String mobile, @RequestParam("pasWord") String pasWord) throws RuntimeException {
        UserEntity userEntity = userRepository.findByMobileAndPasWord(mobile, pasWord);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }

        return ResultUtil.success(userEntity);
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
    public Result loginMobilCode(@RequestParam("mobile") String mobile, @RequestParam("code") String code) throws RuntimeException {
        MsgCodeEntity msgCodeEntity = msgCodeRepository.findByMobile(mobile);
        if (msgCodeEntity == null || !code.equals(msgCodeEntity.getCode())) {

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_ERROR);
        }
        if (System.currentTimeMillis() > msgCodeEntity.getEndTime()) {

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }

        UserEntity userEntity = userRepository.findByMobile(mobile);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        return ResultUtil.success(userEntity);
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
    public Result updatePasWord(@RequestParam("userId") String userId, @RequestParam("pasWord") String pasWord, @RequestParam("newPasWord") String newPasWord) throws RuntimeException {
        UserEntity userEntity = userRepository.findByUserIdAndPasWord(userId, pasWord);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
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
        MsgCodeEntity msgCodeEntity = msgCodeRepository.findByMobile(mobile);
        if (msgCodeEntity == null || !code.equals(msgCodeEntity.getCode())) {

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_ERROR);
        }
        if (System.currentTimeMillis() > msgCodeEntity.getEndTime()) {

            throw new JSGNoSuchElementException(ResultEnum.MOBILE_CODE_INVALID);
        }

        UserEntity userEntity = userRepository.findByMobile(mobile);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        userEntity.setPasWord(pasWord);

        return ResultUtil.success(userRepository.save(userEntity));
    }
}
