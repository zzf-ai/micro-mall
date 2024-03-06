package com.zzf.manager;

import com.zzf.common.log.annotation.EnableLogAspect;
import com.zzf.manager.properties.MinioProperties;
import com.zzf.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zzf
 * @date 2024-01-04
 */
@EnableLogAspect
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzf"})
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
@EnableScheduling
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
