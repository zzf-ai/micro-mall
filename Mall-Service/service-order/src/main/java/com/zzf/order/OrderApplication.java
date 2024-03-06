package com.zzf.order;

import com.zzf.common.anno.EnableUserLoginAuthInterceptor;
import com.zzf.common.anno.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zzf
 * @date 2024-01-21
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.zzf"})
@EnableUserTokenFeignInterceptor
@EnableUserLoginAuthInterceptor
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }
}
