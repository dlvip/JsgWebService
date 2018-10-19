package com.old.time.controller;

import com.old.time.domain.GoodsEntity;
import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
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
        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        long goodsCount = userEntity.getGoodsCount();
        if (goodsCount <= 0) {

            throw new JSGNoSuchElementException(ResultEnum.USER_GOODS_COUNT_0);
        }
        GoodsEntity goodsEntity = new GoodsEntity(userId, picKey, title, price);
        goodsEntity = goodsRepository.save(goodsEntity);
        userEntity.setGoodsCount(userEntity.getGoodsCount() - 1);
        userRepository.save(userEntity);

        return ResultUtil.success(goodsEntity);
    }

    /**
     * 用户获取宝贝列表
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
     * admin获取宝贝列表
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
        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        if ("15093073252".equals(userEntity.getMobile())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }
        List<GoodsEntity> actionEntities = goodsRepository.findGoodsEntitiesByIsDispose(isDispose, PageRequest.of(pageNum, pageSize));

        return ResultUtil.success(actionEntities);
    }

    /**
     * 更新宝贝状态
     *
     * @param userId
     * @param goodsId
     * @param detailId
     * @return
     */
    @RequestMapping(value = "/updateGoodsEntityDetailId")
    public Result updateGoodsEntityDetailId(@RequestParam("userId") String userId, @RequestParam("goodsId") Integer goodsId, @RequestParam("detailId") String detailId) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isGoodsExists = goodsRepository.existsById(goodsId);
        if (!isGoodsExists) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }
        GoodsEntity goodsEntity = goodsRepository.findGoodsEntityByGoodsId(goodsId);
        goodsEntity.setDetailId(detailId);
        goodsEntity.setIsDispose(1);

        return ResultUtil.success(goodsRepository.save(goodsEntity));
    }

    /**
     * 获取宝贝用户
     *
     * @param userId
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/getGoodsEntityUser")
    public Result getGoodsEntityUser(@RequestParam("userId") String userId, @RequestParam("mobile") String mobile) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isFriendExists = userRepository.existsByMobile(mobile);
        if (!isFriendExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        if ("15038124379".equals(userEntity.getMobile())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }

        return ResultUtil.success(userRepository.findByMobile(mobile));
    }


    /**
     * 更新用户剩余次数
     *
     * @param userId
     * @param friendId
     * @param goodsCount
     * @return
     */
    @RequestMapping(value = "/updateUserGoodsCount")
    public Result updateUserGoodsCount(@RequestParam("userId") String userId, @RequestParam("friendId") String friendId, @RequestParam("goodsCount") long goodsCount) {
        boolean isUserExists = userRepository.existsByUserId(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isFriendExists = userRepository.existsByUserId(friendId);
        if (!isFriendExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        if ("15038124379".equals(userEntity.getMobile())) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_DATE);
        }
        UserEntity friendEntity = userRepository.findUserEntityByUserId(friendId);
        friendEntity.setGoodsCount(friendEntity.getGoodsCount() + goodsCount);

        return ResultUtil.success(userRepository.save(friendEntity));
    }
}
