package com.old.time.repository;

import com.old.time.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * 通过用户id和密码查找用户
     *
     * @param userId
     * @param pasWord
     * @return
     */
    UserEntity findByUserIdAndPasWord(String userId, String pasWord);

    /**
     * 通过用户id查找用户
     *
     * @param userId
     * @return
     */
    UserEntity findUserEntityByUserId(String userId);

    /**
     * 通过手机号查找用户
     *
     * @param mobile
     * @return
     */
    UserEntity findUserEntityByMobile(String mobile);

    /**
     * 通过手机号和密码查找用户
     *
     * @param mobile
     * @param pasWord
     * @return
     */
    UserEntity findByMobileAndPasWord(String mobile, String pasWord);

    /**
     * 判断用户是否存在
     *
     * @param userId
     * @return
     */
    boolean existsByUserId(String userId);

    /**
     * 通过手机判断用户是否存在
     *
     * @param mobile
     * @return
     */
    boolean existsByMobile(String mobile);



}
