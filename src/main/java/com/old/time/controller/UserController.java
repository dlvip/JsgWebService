package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.repository.UserRepository;
import com.old.time.utils.GenerateShortUuid;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/user")
public class UserController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取所有用户
     *
     * @return
     */
    @PostMapping(value = "/getUserList")
    @Override
    public Result getControllerList() {

        return ResultUtil.success(userRepository.findAll());
    }

    @PostMapping(value = "/saveUser")
    public Result saveUser(UserEntity userEntity){
        if(userEntity == null){
            userEntity = new UserEntity();

        }
        if(userEntity.getUserId() == null){
            userEntity.setUserId(GenerateShortUuid.generateShortUuid());

        }
        return ResultUtil.success(userRepository.save(userEntity));
    }
}
