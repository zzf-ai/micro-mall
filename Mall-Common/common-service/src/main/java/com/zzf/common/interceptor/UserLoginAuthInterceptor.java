package com.zzf.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.zzf.common.constant.Constants;
import com.zzf.model.entity.user.UserInfo;
import com.zzf.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzf
 * @date 2024-01-18
 */
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userInfoJson = redisTemplate.opsForValue().get(Constants.USER_LOGIN_KEY + request.getHeader("token"));
        AuthContextUtil.setUserInfo(JSON.parseObject(userInfoJson, UserInfo.class));
        return true;
    }
}
