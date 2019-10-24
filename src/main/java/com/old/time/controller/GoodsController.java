package com.old.time.controller;

import com.old.time.domain.GoodsEntity;
import com.old.time.domain.Result;
import com.old.time.repository.GoodsRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/shop")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsRepository goodsRepository;

    @PostMapping(value = "/getGoodsList")
    public Result getGoodsList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<GoodsEntity> goodsEntities = goodsRepository.findAll(PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"))).getContent();

        return ResultUtil.success(goodsEntities);
    }


    /**
     * 添加商品
     *
     * @param goodsId
     * @param goodsPic
     * @return
     */
    @PostMapping(value = "/insertGoods")
    public Result insertGoods(@RequestParam("goodsId") String goodsId, @RequestParam("goodsPic") String goodsPic) {
        if (goodsRepository.existsGoodsEntityByGoodsId(goodsId)) {

            return ResultUtil.success(goodsRepository.findGoodsEntityByGoodsId(goodsId));
        }
        GoodsEntity goodsEntity = new GoodsEntity(goodsId, goodsPic);

        return ResultUtil.success(goodsRepository.save(goodsEntity));
    }
}
