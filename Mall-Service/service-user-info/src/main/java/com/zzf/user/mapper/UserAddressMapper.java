package com.zzf.user.mapper;

import com.zzf.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
@Mapper
public interface UserAddressMapper {
    List<UserAddress> findByUserId(Long userId);

    void updateById(UserAddress userAddress);

    void save(UserAddress userAddress);

    void removeById(Long id);

    UserAddress getById(Long id);
}
