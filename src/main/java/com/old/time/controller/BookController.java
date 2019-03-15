package com.old.time.controller;

import com.old.time.domain.BookEntity;
import com.old.time.domain.Result;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.repository.BookRepository;
import com.old.time.utils.ResultUtil;
import com.old.time.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/book")
public class BookController extends BaseController {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 添加图书信息
     *
     * @param levelNum
     * @param subtitle
     * @param author
     * @param pubdate
     * @param origin_title
     * @param binding
     * @param pages
     * @param images_medium
     * @param images_large
     * @param publisher
     * @param isbn10
     * @param isbn13
     * @param title
     * @param summary
     * @param price
     * @param url
     * @return
     */
    @RequestMapping(value = "/createBookInfo")
    public Result createBookInfo(@RequestParam("levelNum") String levelNum,
                                 @RequestParam("subtitle") String subtitle,
                                 @RequestParam("author") String author,
                                 @RequestParam("pubdate") String pubdate,
                                 @RequestParam("origin_title") String origin_title,
                                 @RequestParam("binding") String binding,
                                 @RequestParam("pages") String pages,
                                 @RequestParam("images_medium") String images_medium,
                                 @RequestParam("images_large") String images_large,
                                 @RequestParam("publisher") String publisher,
                                 @RequestParam("isbn10") String isbn10,
                                 @RequestParam("isbn13") String isbn13,
                                 @RequestParam("title") String title,
                                 @RequestParam("summary") String summary,
                                 @RequestParam("price") String price,
                                 @RequestParam("url") String url) {
        BookEntity bookEntity;
        if ("".equals(isbn10) && "".equals(isbn13)) {
            throw new JSGRuntimeException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);

        }
        if (bookRepository.existsByIsbn10OrIsbn13(isbn10, isbn13)) {
            bookEntity = bookRepository.findBookEntityByIsbn10OrIsbn13(isbn10, isbn13);

        } else {
            bookEntity = bookRepository.save(BookEntity.getInstance(levelNum,
                    subtitle,
                    author,
                    pubdate,
                    origin_title,
                    binding,
                    pages,
                    images_medium,
                    images_large,
                    publisher,
                    isbn10,
                    isbn13,
                    title,
                    summary,
                    price,
                    url));
        }
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
        if ("".equals(isbn)) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_PARAMETER_ERROR);
        } else {
            bookEntity = bookRepository.findBookEntityByIsbn10OrIsbn13(isbn, isbn);

        }
        return ResultUtil.success(bookEntity);
    }
}
