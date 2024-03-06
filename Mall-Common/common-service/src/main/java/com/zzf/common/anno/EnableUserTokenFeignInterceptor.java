package com.zzf.common.anno;

import com.zzf.feign.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zzf
 * @date 2024-01-21
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = UserTokenFeignInterceptor.class)
public @interface EnableUserTokenFeignInterceptor {
}
