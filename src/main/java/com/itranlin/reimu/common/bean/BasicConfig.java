package com.itranlin.reimu.common.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author itranlin
 * @since 2021/4/21 14:33
 */
@Data
@ConfigurationProperties(prefix = "com.itranlin.basic-config")
public class BasicConfig {
    private String basicPackage;
}
