package org.grace.luna.properties;

import lombok.Data;
import org.grace.luna.enums.OssScheme;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 对象存储properties
 *
 * @author Artest
 * Created on 2022/8/30 11:11:14
 */
@ConfigurationProperties("oss")
@Data
public class OssProperties {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String region;

    /**
     * oss体系
     */
    private OssScheme scheme;

}
