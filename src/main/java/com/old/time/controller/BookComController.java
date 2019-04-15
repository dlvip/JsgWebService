package com.old.time.controller;

import com.old.time.domain.BookComEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.BookComRepository;
import com.old.time.repository.BookRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/bookComment")
public class BookComController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookComRepository bookComRepository;

    /**
     * 创建书评
     *
     * @param userId
     * @param bookId
     * @param commennt
     * @return
     */
    @RequestMapping("/createBookComment")
    public Result createBookComment(@RequestParam("userId") String userId, @RequestParam("bookId") Integer bookId, @RequestParam("comment") String commennt) {
        boolean isUser = userRepository.existsByUserId(userId);
        if (!isUser) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        boolean isBook = bookRepository.existsById(bookId);
        if (!isBook) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        }
        return ResultUtil.success(bookComRepository.save(BookComEntity.getInstance(userId, bookId, commennt)));
    }

    /**
     * 获取书评列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getBookComments")
    public Result getBookComments(@RequestParam("userId") String userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUser = userRepository.existsByUserId(userId);
        if (!isUser) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        List<BookComEntity> bookComEntities = bookComRepository.findAll(PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"))).getContent();
        for (BookComEntity bookComEntity : bookComEntities) {
            bookComEntity.setUserEntity(userRepository.findUserEntityByUserId(bookComEntity.getUserId()));
            bookComEntity.setBookEntity(bookRepository.findBookEntityById(bookComEntity.getBookId()));

        }

        return ResultUtil.success(bookComEntities);
    }
}
