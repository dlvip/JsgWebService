package com.old.time.controller;

import com.old.time.domain.Result;
import com.old.time.domain.SignNameEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.SignNameRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/signname")
public class SignNameController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SignNameRepository signNameRepository;

    @RequestMapping(value = "/createSignName")
    public Result createSignName(@RequestParam("userId") String userId, @RequestParam("picUrl") String picUrl, @RequestParam("content") String content) {
        boolean isExist = userRepository.existsByUserId(userId);
        if (!isExist) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        SignNameEntity signNameEntity = new SignNameEntity();
        signNameEntity.setUserId(userId);
        signNameEntity.setPicUrl(picUrl);
        signNameEntity.setContent(content);
        signNameEntity = signNameRepository.save(signNameEntity);

        return ResultUtil.success(signNameEntity);
    }

    @RequestMapping(value = "/getSignNameList")
    public Result getSignNameList(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isExist = userRepository.existsByUserId(userId);
        if (!isExist) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<SignNameEntity> signNameEntities = signNameRepository.findAll(PageRequest.of(pageNum, pageSize)).getContent();

        return ResultUtil.success(signNameEntities);
    }

}
