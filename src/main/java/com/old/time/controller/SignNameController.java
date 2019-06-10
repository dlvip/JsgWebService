package com.old.time.controller;

import com.old.time.domain.Result;
import com.old.time.domain.SignNameEntity;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.BookRepository;
import com.old.time.repository.SignNameRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import com.old.time.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "jiushiguang/signname")
public class SignNameController extends BaseController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SignNameRepository signNameRepository;

    @Autowired
    private PraiseContentController praiseContentController;

    /**
     * 创建打卡
     *
     * @param userId
     * @param picUrl
     * @param content
     * @return
     */
    @RequestMapping(value = "/createSignName")
    public Result createSignName(@RequestParam("userId") String userId, @RequestParam("picUrl") String picUrl, @RequestParam("content") String content, @RequestParam("bookId") String bookId) {
        boolean isExist = userRepository.existsByUserId(userId);
        if (!isExist) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        if (!StringUtils.isNumeric(bookId)) {
            bookId = "0";

        }
        SignNameEntity signNameEntity;
        signNameEntity = signNameRepository.save(SignNameEntity.getInstance(userId, picUrl, content, Integer.parseInt(bookId)));

        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        signNameEntity.setUserEntity(userEntity);

        return ResultUtil.success(signNameEntity);
    }

    /**
     * 分页获取打卡列表
     *
     * @param userId
     * @param friendId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getSignNameList")
    public Result getSignNameList(@RequestParam("userId") String userId, @RequestParam("friendId") String friendId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<SignNameEntity> signNameEntities;
        if (friendId != null && !"".equals(friendId)) {
            signNameEntities = signNameRepository.findSignNameEntitiesByUserId(friendId, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        } else {
            signNameEntities = signNameRepository.findAll(PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"))).getContent();

        }
        for (SignNameEntity signNameEntity : signNameEntities) {
            signNameEntity.setUserEntity(userRepository.findUserEntityByUserId(signNameEntity.getUserId()));
            signNameEntity.setPaiseCount(praiseContentController.getPraiseCount(signNameEntity.getId()));
            boolean isExist = userRepository.existsByUserId(userId);
            if (!isExist) {
                signNameEntity.setIsPaise(praiseContentController.isPraiseUser(userId, signNameEntity.getId()));

            }
            signNameEntity.setBookEntity(bookRepository.findBookEntityById(signNameEntity.getBookId()));

        }
        return ResultUtil.success(signNameEntities);
    }

    /**
     * 分页获取打卡列表
     *
     * @param bookId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getBookSignNameList")
    public Result getBookSignNameList(@RequestParam("bookId") Integer bookId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<SignNameEntity> signNameEntities = signNameRepository.findSignNameEntitiesByBookId(bookId, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));
        for (SignNameEntity signNameEntity : signNameEntities) {
            signNameEntity.setUserEntity(userRepository.findUserEntityByUserId(signNameEntity.getUserId()));
            signNameEntity.setPaiseCount(praiseContentController.getPraiseCount(signNameEntity.getId()));
            signNameEntity.setBookEntity(bookRepository.findBookEntityById(signNameEntity.getBookId()));

        }
        return ResultUtil.success(signNameEntities);
    }
}
