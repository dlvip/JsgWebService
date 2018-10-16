package com.old.time.controller;

import com.old.time.domain.GoodsEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.GoodsRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/goods")
public class GoodsController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoodsRepository goodsRepository;


    /**
     * 添加宝贝
     *
     * @param userId
     * @param picKey
     * @param title
     * @param price
     * @return
     */
    @RequestMapping(value = "/insertGoods")
    public Result insertGoods(@RequestParam("userId") String userId, @RequestParam("picKey") String picKey, @RequestParam("title") String title, @RequestParam("price") String price) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        GoodsEntity goodsEntity = GoodsEntity.getInstance(userId, picKey, title, price);

        return ResultUtil.success(goodsRepository.save(goodsEntity));
    }

    /**
     * 获取宝贝列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getGoodsList")
    public Result getGoodsList(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<GoodsEntity> actionEntities = goodsRepository.findGoodsEntitiesByUserId(userId, PageRequest.of(pageNum, pageSize));

        return ResultUtil.success(actionEntities);
    }

    /**
     * 获取所有宝贝列表
     *
     * @param userId
     * @param isDispose
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getGoodsListByAdmin")
    public Result getGoodsListByAdmin(@RequestParam("userId") String userId, @RequestParam("isDispose") Integer isDispose, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<GoodsEntity> actionEntities = goodsRepository.findGoodsEntitiesByIsDispose(isDispose == 1, PageRequest.of(pageNum, pageSize));

        return ResultUtil.success(actionEntities);
    }

}
