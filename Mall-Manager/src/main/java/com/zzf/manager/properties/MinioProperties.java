package com.zzf.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zzf
 * @date 2024-01-07
 */
@Data
@ConfigurationProperties(prefix="mall.minio") //读取节点
public class MinioProperties {
    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;
}
