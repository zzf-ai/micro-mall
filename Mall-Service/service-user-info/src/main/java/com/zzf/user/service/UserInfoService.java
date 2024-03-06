package com.zzf.user.service;

import com.zzf.model.dto.h5.UserLoginDto;
import com.zzf.model.dto.h5.UserRegisterDto;
import com.zzf.model.vo.user.UserInfoVo;

/**
 * @author zzf
 * @date 2024-01-18
 */
public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    Object login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
