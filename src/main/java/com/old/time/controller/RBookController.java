package com.old.time.controller;

import com.old.time.domain.BookTabEntity;
import com.old.time.domain.ItemBookEntity;
import com.old.time.domain.Result;
import com.old.time.repository.RBookRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/book")
public class RBookController extends BaseController {

    @Autowired
    private RBookRepository rBookRepository;

    /**
     * 精选：00：重磅推荐、01：热门精选、02：都市·猎艳、03：暧昧·异能、04：小编·推荐
     * 男频：10：重磅推荐、11：热门精选、12：玄幻·奇幻、13：历史·军旅、14：游戏·竞技
     * 女频：20：重磅推荐、21：热门精选、22：现代·都市、23：灵异·穿越、24：美文·同人
     */
    /**
     * 获取精选
     *
     * @return
     */
    @RequestMapping(value = "/getRecommendRBook")
    public Result getRecommendRBook(String aType) {
        List<ItemBookEntity> itemBookEntities = new ArrayList<>();
        switch (aType) {
            case "0"://推荐
                itemBookEntities.add(ItemBookEntity.getInstance("重磅推荐", rBookRepository.findRBookEntitiesByWeight("00", PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("热门精选", rBookRepository.findRBookEntitiesByWeight("01", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("都市·猎艳", rBookRepository.findRBookEntitiesByWeight("02", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("暧昧·异能", rBookRepository.findRBookEntitiesByWeight("03", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("小编·推荐", rBookRepository.findRBookEntitiesByWeight("04", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
            case "1"://男频
                itemBookEntities.add(ItemBookEntity.getInstance("重磅推荐", rBookRepository.findRBookEntitiesByWeight("10", PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("热门精选", rBookRepository.findRBookEntitiesByWeight("11", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("玄幻·奇幻", rBookRepository.findRBookEntitiesByWeight("12", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("历史·军旅", rBookRepository.findRBookEntitiesByWeight("13", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("游戏·竞技", rBookRepository.findRBookEntitiesByWeight("14", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
            case "2"://女频
                itemBookEntities.add(ItemBookEntity.getInstance("重磅推荐", rBookRepository.findRBookEntitiesByWeight("20", PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("热门精选", rBookRepository.findRBookEntitiesByWeight("21", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("现代·都市", rBookRepository.findRBookEntitiesByWeight("22", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("灵异·穿越", rBookRepository.findRBookEntitiesByWeight("23", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("美文·同人", rBookRepository.findRBookEntitiesByWeight("24", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
        }

        return ResultUtil.success(itemBookEntities);
    }

    /**
     * @param aTypt
     * @return
     */
    @RequestMapping(value = "/getTypeRBookList")
    public Result getTypeRBookList(String aTypt, Integer startNum, Integer pageSize) {

        return ResultUtil.success(rBookRepository.findRBookEntitiesByAType(aTypt, PageRequest.of(startNum, pageSize, new Sort(Sort.Direction.DESC, "id"))));
    }

    /**
     * 获取类型推荐
     *
     * @return
     */
    @RequestMapping(value = "/getTypeRecommendBooks")
    public Result getTypeRecommendBooks(String aTypt, Integer startNum, Integer pageSize) {

        return ResultUtil.success(rBookRepository.findRBookEntitiesByAType(aTypt, PageRequest.of(startNum, pageSize, new Sort(Sort.Direction.ASC, "id"))));
    }

    /**
     * 获取类型
     *
     * @return
     */
    @RequestMapping(value = "/getPeopleTypeList")
    public Result getPeopleTypeList() {
        List<BookTabEntity> bookTabEntities = new ArrayList<>();
        bookTabEntities.add(BookTabEntity.getInstance(0));
        bookTabEntities.add(BookTabEntity.getInstance(1));

        return ResultUtil.success(bookTabEntities);
    }
}
