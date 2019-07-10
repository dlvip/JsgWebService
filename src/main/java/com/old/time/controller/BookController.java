package com.old.time.controller;

import com.old.time.domain.*;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.*;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/book")
public class BookController extends BaseController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SignNameRepository signNameRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicVideoBookRepository topicVideoBookRepository;

    /**
     * 添加图书信息
     *
     * @param levelNum
     * @param author
     * @param pubdate
     * @param binding
     * @param images_large
     * @param publisher
     * @param isbn10
     * @param isbn13
     * @param title
     * @param summary
     * @param price
     * @return
     */
    @RequestMapping(value = "/createBookInfo")
    public Result createBookInfo(@RequestParam("levelNum") String levelNum,
                                 @RequestParam("author") String author,
                                 @RequestParam("pubdate") String pubdate,
                                 @RequestParam("binding") String binding,
                                 @RequestParam("images_large") String images_large,
                                 @RequestParam("publisher") String publisher,
                                 @RequestParam("isbn10") String isbn10,
                                 @RequestParam("isbn13") String isbn13,
                                 @RequestParam("title") String title,
                                 @RequestParam("summary") String summary,
                                 @RequestParam("price") String price) {
        BookEntity bookEntity;
        if ("".equals(isbn10) && "".equals(isbn13)) {
            throw new JSGRuntimeException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);

        }
        if (bookRepository.existsByIsbn10OrIsbn13(isbn10, isbn13)) {
            bookEntity = bookRepository.findBookEntityByIsbn10OrIsbn13(isbn10, isbn13);

        } else {
            bookEntity = bookRepository.save(BookEntity.getInstance(levelNum,
                    author,
                    pubdate,
                    binding,
                    images_large,
                    publisher,
                    isbn10,
                    isbn13,
                    title,
                    summary,
                    price));
        }
        //创建话题
        TopicEntity topicEntity;
        if (!topicRepository.existsTopicEntityByTopic(bookEntity.getTitle())) {
            topicEntity = topicRepository.save(TopicEntity.getInstance("", "#" + bookEntity.getTitle(), bookEntity.getImages_large()));

        } else {
            topicEntity = topicRepository.findTopicEntityByTopic("#" + bookEntity.getTitle());

        }
        //创建关联
        TopicVideoBookEntry topicVideoBookEntry = topicVideoBookRepository.findTopicVideoBookEntryByTopicId(topicEntity.getId());
        if (topicVideoBookEntry == null) {
            topicVideoBookEntry = TopicVideoBookEntry.instance(-1, topicEntity.getId(), bookEntity.getId());

        } else {
            topicVideoBookEntry.setBookId(bookEntity.getId());

        }
        topicVideoBookRepository.save(topicVideoBookEntry);

        return ResultUtil.success(bookEntity);
    }

    /**
     * 获取图书信息
     *
     * @param isbn
     * @return
     */
    @RequestMapping(value = "/getBookInfo")
    public Result getBookInfo(@RequestParam("isbn") String isbn) {
        BookEntity bookEntity;
        if ("".equals(isbn) || null == isbn) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        bookEntity = bookRepository.findBookEntityByIsbn10OrIsbn13(isbn, isbn);

        return ResultUtil.success(bookEntity);
    }

    /**
     * 图书列表(用户)
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getUserBookList")
    public Result getUserBookList(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isExist = userRepository.existsByUserId(userId);
        if (!isExist) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"));
        List<SignNameEntity> signNameEntities = signNameRepository.findSignNameEntitiesByUserId(userId, pageable);
        List<BookEntity> bookEntities = new ArrayList<>();
        bookEntities.clear();
        for (SignNameEntity signNameEntity : signNameEntities) {
            bookEntities.add(bookRepository.findBookEntityById(signNameEntity.getBookId()));

        }
        return ResultUtil.success(bookEntities);
    }

    /**
     * 图书列表（分页）
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getBookEntities")
    public Result getBookEntities(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"));
        List<BookEntity> bookEntities = bookRepository.findAll(pageable).getContent();

        return ResultUtil.success(bookEntities);
    }

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
    @RequestMapping(value = "/getRecommendBook")
    public Result getRecommendBook(@RequestParam("tabId") String aType) {
        List<ItemBookEntity> itemBookEntities = new ArrayList<>();
        switch (aType) {
            case "0"://推荐
                itemBookEntities.add(ItemBookEntity.getInstance("重磅推荐", bookRepository.findRBookEntitiesByWeight("00", PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("热门精选", bookRepository.findRBookEntitiesByWeight("01", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("都市·猎艳", bookRepository.findRBookEntitiesByWeight("02", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("暧昧·异能", bookRepository.findRBookEntitiesByWeight("03", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("小编·推荐", bookRepository.findRBookEntitiesByWeight("04", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
            case "1"://男频
                itemBookEntities.add(ItemBookEntity.getInstance("重磅推荐", bookRepository.findRBookEntitiesByWeight("10", PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("热门精选", bookRepository.findRBookEntitiesByWeight("11", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("玄幻·奇幻", bookRepository.findRBookEntitiesByWeight("12", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("历史·军旅", bookRepository.findRBookEntitiesByWeight("13", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("游戏·竞技", bookRepository.findRBookEntitiesByWeight("14", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
            case "2"://女频
                itemBookEntities.add(ItemBookEntity.getInstance("重磅推荐", bookRepository.findRBookEntitiesByWeight("20", PageRequest.of(0, 4, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("热门精选", bookRepository.findRBookEntitiesByWeight("21", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("现代·都市", bookRepository.findRBookEntitiesByWeight("22", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("灵异·穿越", bookRepository.findRBookEntitiesByWeight("23", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));
                itemBookEntities.add(ItemBookEntity.getInstance("美文·同人", bookRepository.findRBookEntitiesByWeight("24", PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "id")))));

                break;
        }

        return ResultUtil.success(itemBookEntities);
    }

    /**
     * @param tabId
     * @return
     */
    @RequestMapping(value = "/getTabBookList")
    public Result getTypeRBookList(String tabId, Integer startNum, Integer pageSize) {

        return ResultUtil.success(bookRepository.findBookEntitiesByTabId(tabId, PageRequest.of(startNum, pageSize, new Sort(Sort.Direction.DESC, "id"))));
    }

    /**
     * 获取类型推荐
     *
     * @return
     */
    @RequestMapping(value = "/getWeightRecommendBooks")
    public Result getTsbRecommendBooks(String weight, Integer startNum, Integer pageSize) {

        return ResultUtil.success(bookRepository.findRBookEntitiesByWeight(weight, PageRequest.of(startNum, pageSize, new Sort(Sort.Direction.ASC, "id"))));
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
