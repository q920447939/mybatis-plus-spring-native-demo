package com.withmes.config;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 其它配置
 *
 * @author CGM
 */
@Data
@Setter
@Configuration
@ConfigurationProperties(prefix = "extra")
public class ExtraConfig {
    /**
     * 用户默认头像的地址
     */
    private String defaultAvatar;

    /**
     * 证书
     */
    private String license;
}
