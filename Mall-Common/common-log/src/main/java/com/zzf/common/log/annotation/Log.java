package com.zzf.common.log.annotation;

import com.zzf.common.log.enums.BusinessType;
import com.zzf.common.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    //模块名称
    public String title();
    //操作人类别
    public OperatorType operatorType() default OperatorType.MANAGE;
    // 业务类型
    public BusinessType businessType();
    //是否保存请求的参数
    public boolean isSaveRequestData() default true;
    //是否保存响应的参数
    public boolean isSaveResponseData() default true;
}
