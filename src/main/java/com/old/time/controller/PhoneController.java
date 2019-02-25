package com.old.time.controller;

import com.old.time.domain.PhoneInfoEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.PhoneRepository;
import com.old.time.utils.ResultUtil;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/phone")
public class PhoneController extends BaseController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    FastMailController fastMailController;

    @PostMapping(value = "/savePhoneList")
    public Result savePhoneList() {
        String path = PhoneController.class.getResource("/assets").getPath();
        File jsonFile = null;
        try {
            jsonFile = ResourceUtils.getFile(path + "/phone.json");
            String json = FileUtils.readFileToString(jsonFile, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                PhoneInfoEntity phoneInfoEntity = new PhoneInfoEntity();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                phoneInfoEntity.setAreacode(jsonObject.getString("areacode"));
                phoneInfoEntity.setCard(jsonObject.getString("card"));
                phoneInfoEntity.setCity(jsonObject.getString("city"));
                phoneInfoEntity.setCompany(jsonObject.getString("company"));
                phoneInfoEntity.setPhone(jsonObject.getString("phone"));
                phoneInfoEntity.setProvince(jsonObject.getString("province"));
                phoneInfoEntity.setZip(jsonObject.getString("zip"));
                if (!phoneRepository.existsPhoneInfoEntityByPhone(phoneInfoEntity.getPhone())) {
                    phoneRepository.save(phoneInfoEntity);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        fastMailController.savePhoneList();

        return ResultUtil.success();
    }

    /**
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
