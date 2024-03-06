package com.zzf.user.service.impl;

import com.zzf.model.entity.user.UserAddress;
import com.zzf.user.mapper.UserAddressMapper;
import com.zzf.user.mapper.UserRegionMapper;
import com.zzf.user.service.UserAddressService;
import com.zzf.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private UserRegionMapper userRegionMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findByUserId(userId);
    }

    @Override
    public void save(UserAddress userAddress) {
        //获取用户userID
        Long userId = AuthContextUtil.getUserInfo().getId();
        userAddress.setUserId(userId);
        String province = userRegionMapper.selectByCode(userAddress.getProvinceCode());
        String city = userRegionMapper.selectByCode(userAddress.getCityCode());
        String district = userRegionMapper.selectByCode(userAddress.getDistrictCode());
        StringBuilder fullAddressBuilder = new StringBuilder();
        fullAddressBuilder.append(province).append(city).append(district);
        String fullAddress = fullAddressBuilder.toString();
        userAddress.setFullAddress(fullAddress);
        userAddressMapper.save(userAddress);
    }

    @Override
    public void removeById(Long id) {
        userAddressMapper.removeById(id);
    }

    @Override
    public void updateById(UserAddress userAddress) {
        String province = userRegionMapper.selectByCode(userAddress.getProvinceCode());
        String city = userRegionMapper.selectByCode(userAddress.getCityCode());
        String district = userRegionMapper.selectByCode(userAddress.getDistrictCode());
        StringBuilder fullAddressBuilder = new StringBuilder();
        fullAddressBuilder.append(province).append(city).append(district);
        String fullAddress = fullAddressBuilder.toString();
        userAddress.setFullAddress(fullAddress);
        userAddressMapper.updateById(userAddress);
    }

    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.getById(id);
    }

}
