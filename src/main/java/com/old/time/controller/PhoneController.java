package com.old.time.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.old.time.domain.PhoneBeanEntity;
import com.old.time.domain.PhoneInfoEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.PhoneBeanRepository;
import com.old.time.repository.PhoneRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "jiushiguang/phone")
public class PhoneController extends BaseController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private PhoneBeanRepository phoneBeanRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存通讯录
     *
     * @return
     */
    @RequestMapping(value = "/savePhoneBeanList")
    public Result savePhoneBeanList(@RequestParam("userId") String userId, @RequestParam("phoneListJson") String json) {
        boolean exists = userRepository.existsByUserId(userId);
        if (!exists) {

            throw new JSGRuntimeException(ResultEnum.USER_NON_EXISTENT);
        }
        long count = phoneBeanRepository.countAllByUserId(userId);
        ArrayList<PhoneBeanEntity> phoneBeanEntities = JSON.parseObject(json, new TypeReference<ArrayList<PhoneBeanEntity>>(){});

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < phoneBeanEntities.size(); i++) {
            String name = phoneBeanEntities.get(i).getName();
            String number = phoneBeanEntities.get(i).getNumber();
            PhoneBeanEntity phoneBeanEntity;
            if (count == 0) {
                phoneBeanEntity = new PhoneBeanEntity();
                phoneBeanEntity.setName(name);
                phoneBeanEntity.setNumber(number);
                phoneBeanEntity.setUserId(userId);

            } else {
                phoneBeanEntity = phoneBeanRepository.findPhoneBeanEntityByUserIdAndName(userId, name);
                if (phoneBeanEntity == null) {
                    phoneBeanEntity = new PhoneBeanEntity();
                    phoneBeanEntity.setName(name);
                    phoneBeanEntity.setNumber(number);
                    phoneBeanEntity.setUserId(userId);

                } else {
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append(number);
                    String[] numStr = phoneBeanEntity.getNumber().split(",");
                    for (String phone : numStr) {
                        if (!number.contains(phone)) {
                            stringBuilder.append(",");
                            stringBuilder.append(phone);

                        }
                    }
                    phoneBeanEntity.setNumber(stringBuilder.toString());
                }
            }
            String sortKey = phoneBeanEntities.get(i).getSortKey();
            String photo = phoneBeanEntities.get(i).getPhoto();
            phoneBeanEntity.setSortKey(sortKey);
            phoneBeanEntity.setPhoto(photo);
            phoneBeanRepository.save(phoneBeanEntity);
        }
        List<PhoneBeanEntity> phoneInfoEntities = phoneBeanRepository.findPhoneBeanEntitiesByUserId(userId);
        return ResultUtil.success(phoneInfoEntities);
    }

    /**
     * 查找单个用户下的联系人信息
     *
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUserSinglePhoneBean")
    public Result getUserSinglePhoneBean(@RequestParam("userId") String userId, @RequestParam("id") String id) {
        boolean exists = userRepository.existsByUserId(userId);
        if (!exists) {

            throw new JSGRuntimeException(ResultEnum.USER_NON_EXISTENT);
        }
        PhoneBeanEntity phoneBeanEntity = phoneBeanRepository.findPhoneBeanEntityByUserIdAndId(userId, Integer.parseInt(id));

        return ResultUtil.success(phoneBeanEntity);
    }

    /**
     * 获取用户通讯录
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getPhoneBeanList")
    public Result getPhoneBeanList(@RequestParam("userId") String userId) {
        boolean exists = userRepository.existsByUserId(userId);
        List<PhoneBeanEntity> phoneBeanEntities;
        if (exists) {
            phoneBeanEntities = phoneBeanRepository.findPhoneBeanEntitiesByUserId(userId);

        } else {
            phoneBeanEntities = new ArrayList<>();

        }
        return ResultUtil.success(phoneBeanEntities);
    }

    /**
     * 保存手机归属地信息
     *
     * @param phoneInfoEntity
     * @return
     */
    @PostMapping(value = "/savePhoneInfo")
    public Result savePhoneInfo(PhoneInfoEntity phoneInfoEntity) {
        if (phoneInfoEntity == null || null == phoneInfoEntity.getPhone()) {

            throw new JSGRuntimeException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        boolean isExists = phoneRepository.existsPhoneInfoEntityByPhone(phoneInfoEntity.getPhone());
        if (!isExists) {
            phoneRepository.save(phoneInfoEntity);

        }

        return ResultUtil.success();
    }

    /**
     * 获取手机号归属地信息
     *
     * @param telePhone
     * @return
     */
    @PostMapping(value = "/getPhoneInfo")
    public Result getPhoneInfo(@RequestParam("mobile") String telePhone) {
        if (null == telePhone) {

            throw new JSGRuntimeException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        String[] phoneList = telePhone.split(",");
        List<PhoneInfoEntity> phoneInfoEntities = new ArrayList<>();
        phoneInfoEntities.clear();
        for (String phone : phoneList) {
            PhoneInfoEntity phoneInfoEntity = phoneRepository.findPhoneInfoEntityByPhone(phone);
            if (phoneInfoEntity == null) {
                phoneInfoEntity = new PhoneInfoEntity();
                phoneInfoEntity.setPhone(phone);

            }
            phoneInfoEntities.add(phoneInfoEntity);
        }
        return ResultUtil.success(phoneInfoEntities);
    }
}
