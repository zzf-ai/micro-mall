package com.zzf.manager.service.impl;

import com.zzf.common.log.service.AsyncOperLogService;
import com.zzf.manager.mapper.SysOperLogMapper;
import com.zzf.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
