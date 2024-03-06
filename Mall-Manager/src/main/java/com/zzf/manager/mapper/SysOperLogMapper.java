package com.zzf.manager.mapper;

import com.zzf.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Mapper
public interface SysOperLogMapper {
    void insert(SysOperLog sysOperLog);
}
