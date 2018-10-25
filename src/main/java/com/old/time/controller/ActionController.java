package com.old.time.controller;

import com.old.time.domain.ActionEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.ActionRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/action")
public class ActionController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionRepository actionRepository;

    /**
     * 添加活动
     *
     * @param userId
     * @param title
     * @param pic
     * @param url
     * @param brief
     * @param longitude
     * @param latitude
     * @param address
     * @return
     */
    @RequestMapping(value = "/insertAction")
    public Result insertAction(@RequestParam("userId") String userId, @RequestParam("title") String title, @RequestParam("pic") String pic, @RequestParam("url") String url, @RequestParam("brief") String brief, @RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, @RequestParam("address") String address, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        ActionEntity actionEntity = new ActionEntity(userId, title, pic, url, brief, longitude, latitude, address, startTime, endTime);

        return ResultUtil.success(actionRepository.save(actionEntity));
    }

    /**
     * 获取活动详情
     *
     * @param userId
     * @param actionId
     * @return
     */
    @RequestMapping(value = "/getActionDetail")
    public Result getActionDetail(@RequestParam("userId") String userId, @RequestParam("actionId") Integer actionId) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isExistsAction = actionRepository.existsActionEntityByActionId(actionId);
        if(!isExistsAction){

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }

        ActionEntity actionEntity = actionRepository.findActionEntityByActionId(actionId);

        return ResultUtil.success(actionEntity);
    }



    /**
     * 获取活动列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getActionList")
    public Result getActionList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<ActionEntity> actionEntities = actionRepository.findAll(PageRequest.of(pageNum, pageSize)).getContent();

        return ResultUtil.success(actionEntities);
    }

    /**
     * 获取活动列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getUserActionList")
    public Result getUserActionList(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<ActionEntity> actionEntities = actionRepository.findActionEntityByUserId(userId, PageRequest.of(pageNum, pageSize));

        return ResultUtil.success(actionEntities);
    }
}
