package com.zzf.common.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Getter
@AllArgsConstructor
public enum BusinessType {
    INSERT("添加"),
    UPDATE("修改"),
    DELETE("删除"),
    ASSIGN_ROLE("分配角色"),
    ASSIGN_MENU("分配菜单"),
    EXPORT("导出"),
    IMPORT("导入"),
    AUDIT("审核"),
    ON_OFF("上下架");

    private String value;
}
