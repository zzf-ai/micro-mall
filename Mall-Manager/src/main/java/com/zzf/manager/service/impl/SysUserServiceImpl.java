package com.zzf.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.common.constant.Constants;
import com.zzf.common.exception.CustomException;
import com.zzf.manager.mapper.SysRoleMapper;
import com.zzf.manager.mapper.SysRoleUserMapper;
import com.zzf.manager.mapper.SysUserMapper;
import com.zzf.manager.service.SysUserService;
import com.zzf.model.dto.system.AssginRoleDto;
import com.zzf.model.dto.system.LoginDto;
import com.zzf.model.dto.system.SysRoleDto;
import com.zzf.model.dto.system.SysUserDto;
import com.zzf.model.entity.system.SysRole;
import com.zzf.model.entity.system.SysUser;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.model.vo.system.LoginVo;
import com.zzf.model.vo.system.SysMenuVo;
import com.zzf.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zzf
 * @date 2024-01-04
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 校验验证码是否正确，获取登录参数中和redis中存储的验证码
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();

        String redisCode = redisTemplate.opsForValue().get(Constants.USER_VALIDATE_KEY + codeKey);

        //验证码为空或者不一致
        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)){
            throw new CustomException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //如果一致，删除redis里面验证码
        redisTemplate.delete(Constants.USER_VALIDATE_KEY + codeKey);

        String userName = loginDto.getUserName();
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if(sysUser == null){
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        String db_password = sysUser.getPassword();
        String input_password = loginDto.getPassword();
        input_password = DigestUtils.md5DigestAsHex(input_password.getBytes());
        if(!input_password.equals(db_password)){
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(Constants.USER_LOGIN_KEY+token, JSON.toJSONString(sysUser), 7, TimeUnit.DAYS);

        //返回vo
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get(Constants.USER_LOGIN_KEY + token);
        return JSON.parseObject(userJson, SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(Constants.USER_LOGIN_KEY + token);
    }

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        PageInfo pageInfo = new PageInfo(sysUserList) ;
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        //判断用户名不能重复
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if(dbSysUser != null){
            throw new CustomException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //密码加密
        String db_password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(db_password);
        sysUser.setStatus(1);
        sysUserMapper.saveSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }

    @Transactional
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //根据userId删除用户之前的分配角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());

        //插入新分配的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        for (Long roleId : roleIdList) {
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(), roleId);
        }
    }
}
