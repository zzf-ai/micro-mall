package com.zzf.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzf.common.constant.Constants;
import com.zzf.common.exception.CustomException;
import com.zzf.model.dto.h5.UserLoginDto;
import com.zzf.model.dto.h5.UserRegisterDto;
import com.zzf.model.entity.user.UserInfo;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.model.vo.user.UserInfoVo;
import com.zzf.user.mapper.UserInfoMapper;
import com.zzf.user.service.UserInfoService;
import com.zzf.utils.AuthContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zzf
 * @date 2024-01-18
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(nickName) ||
                StringUtils.isEmpty(code)) {
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }
        String codeValueRedis = redisTemplate.opsForValue().get(Constants.REGISTER_VALIDATE_KEY + username);

        //校验验证码
        if(!code.equals(codeValueRedis)) {
            throw new CustomException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //用户名不能重复
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null != userInfo) {
            throw new CustomException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //封装用户
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar(Constants.USER_AVATAR_KEY);

        //保存
        userInfoMapper.save(userInfo);

        // 删除Redis中的数据
        redisTemplate.delete(Constants.USER_VALIDATE_KEY + username);
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)) {
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }

        //查询用户信息
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null == userInfo) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }
        //如果用户被禁用
        if(userInfo.getStatus() == 0){
            throw new CustomException(ResultCodeEnum.ACCOUNT_STOP);
        }
        //校验密码
        String db_password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!db_password.equals(userInfo.getPassword())){
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(Constants.USER_LOGIN_KEY + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;
    }
}
