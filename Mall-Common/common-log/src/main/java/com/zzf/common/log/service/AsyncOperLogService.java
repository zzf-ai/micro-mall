package com.zzf.common.log.service;

import com.zzf.model.entity.system.SysOperLog;

/**
 * @author zzf
 * @date 2024-01-13
 */
public interface AsyncOperLogService {
    void saveSysOperLog(SysOperLog sysOperLog);
}
