package com.zzf.pay;

import com.zzf.common.anno.EnableUserLoginAuthInterceptor;
import com.zzf.pay.properties.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zzf
 * @date 2024-01-23
 */
@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableConfigurationProperties(value = { AlipayProperties.class })
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }
}
