package com.zzf.user.service;

import com.zzf.model.entity.user.UserAddress;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    void save(UserAddress userAddress);

    void removeById(Long id);

    void updateById(UserAddress userAddress);

    UserAddress getById(Long id);
}
