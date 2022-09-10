package com.itranlin.reimu.common.bean.tencent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.itranlin.tencent.cos")
public class CosConfig {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
}
