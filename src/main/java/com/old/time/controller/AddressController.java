package com.old.time.controller;

import com.old.time.domain.AddressEntity;
import com.old.time.domain.Result;
import com.old.time.domain.UserEntity;
import com.old.time.enums.ResultEnum;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.repository.AddressRepository;
import com.old.time.repository.UserRepository;
import com.old.time.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jiushiguang/address")
public class AddressController extends BaseController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 添加地址信息
     *
     * @param userId
     * @param mobile
     * @param name
     * @param county
     * @param area
     * @param address
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/saveAddress")
    public Result saveAddress(@RequestParam("userId") Integer userId, @RequestParam("mobile") String mobile, @RequestParam("name") String name, @RequestParam("county") String county, @RequestParam("area") String area, @RequestParam("address") String address) throws RuntimeException {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }

        AddressEntity addressEntity = new AddressEntity(userId, mobile, name, county, area, address);

        return ResultUtil.success(addressRepository.save(addressEntity));
    }

    /**
     * 修改地址信息
     *
     * @param userId    用户id
     * @param addressId 地址id
     * @param mobile    收件人手机号
     * @param name      收件人名称
     * @param county    收件人县乡
     * @param area      收件人地区
     * @param address   门牌号
     * @return
     * @throws RuntimeException
     */
    @PostMapping(value = "/updateAddress")
    public Result updateAddress(@RequestParam("userId") Integer userId, @RequestParam("addressId") Integer addressId, @RequestParam("mobile") String mobile, @RequestParam("name") String name, @RequestParam("county") String county, @RequestParam("area") String area, @RequestParam("address") String address) throws RuntimeException {
        boolean isUserExists = userRepository.existsById(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        if (addressEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_ADDRESS_NON);
        }
        if (addressEntity.getUserId().equals(userId)) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_PERMISSION);
        }
        addressEntity.setName(name);
        addressEntity.setMobile(mobile);
        addressEntity.setCounty(county);
        addressEntity.setArea(area);
        addressEntity.setAddress(address);

        return ResultUtil.success(addressRepository.save(addressEntity));
    }

    /**
     * 删除地址
     *
     * @param userId
     * @param addressId
     * @return
     */
    @PostMapping(value = "/deleteAddress")
    public Result deleteAddress(@RequestParam("userId") Integer userId, @RequestParam("addressId") Integer addressId) {
        boolean isUserExists = userRepository.existsById(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        if (addressEntity == null) {

            throw new JSGNoSuchElementException(ResultEnum.USER_ADDRESS_NON);
        }
        if (addressEntity.getUserId().equals(userId)) {

            throw new JSGNoSuchElementException(ResultEnum.CURRENCY_MSG_NON_PERMISSION);
        }
        addressRepository.deleteById(addressId);

        return ResultUtil.success();
    }

    /**
     * 获取地址列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/getAddressList")
    public Result getAddressList(@RequestParam("userId") Integer userId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        boolean isUserExists = userRepository.existsById(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }

        return ResultUtil.success(addressRepository.findAddressEntitiesByUserId(userId, PageRequest.of(pageNum, pageSize)));
    }

    /**
     * 获取地址列表
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/getAddressEntities")
    public Result getAddressList(@RequestParam("userId") Integer userId) {
        boolean isUserExists = userRepository.existsById(userId);
        if (!isUserExists) {

            throw new JSGNoSuchElementException(ResultEnum.USER_NON_EXISTENT);
        }

        return ResultUtil.success(addressRepository.findAddressEntitiesByUserId(userId));
    }
}
