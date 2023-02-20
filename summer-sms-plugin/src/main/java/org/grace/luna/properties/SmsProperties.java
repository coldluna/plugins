package org.grace.luna.properties;

import lombok.Data;
import org.grace.luna.enums.SmsScheme;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信属性
 *
 * @author Artest Wang
 * Created on 2022/9/21 11:51:37
 */
@ConfigurationProperties(SmsProperties.PREFIX)
@Data
public class SmsProperties {

    static final String PREFIX = "sms";

    private String accessKeyId;

    private String accessKeySecret;

    private String domain;

    private String region;

    /**
     * 阿里云专用
     */
    private String product;

    /**
     * 腾讯云专用
     */
    private String endpoint;

    /**
     * 腾讯云专用
     */
    private String appId;

    private SmsScheme scheme;

}
