package com.zzf.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-05
 */
@Data
@ConfigurationProperties(prefix = "mall.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;
}
