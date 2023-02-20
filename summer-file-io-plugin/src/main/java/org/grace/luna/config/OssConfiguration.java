package org.grace.luna.config;

import com.aliyun.oss.OSSClient;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import io.minio.MinioClient;
import org.grace.luna.properties.OssProperties;
import org.grace.luna.template.oss.AliOssTemplate;
import org.grace.luna.template.oss.AmazonOssTemplate;
import org.grace.luna.template.oss.MinioTemplate;
import org.grace.luna.template.oss.OssTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对象存储配置类
 *
 * @author Artest
 * Created on 2022/8/30 11:26:16
 */
@EnableConfigurationProperties(OssProperties.class)
@Configuration
@ConditionalOnProperty("oss.scheme")
public class OssConfiguration {

    @Bean
    @ConditionalOnProperty(name = "oss.scheme", havingValue = "aliyun")
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate aliOssTemplate(OssProperties ossProperties) {
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        return new AliOssTemplate(ossClient);
    }

    @Bean
    @ConditionalOnProperty(name = "oss.scheme", havingValue = "amazon")
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate amazonOssTemplate(OssProperties ossProperties) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(ossProperties.getEndpoint(), ossProperties.getRegion());
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        AmazonS3 amazonS3 = AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(awsStaticCredentialsProvider)
                .disableChunkedEncoding()
                .withPathStyleAccessEnabled(true)
                .build();
        return new AmazonOssTemplate(amazonS3);
    }

    @Bean
    @ConditionalOnProperty(name = "oss.scheme", havingValue = "minio")
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate minioTemplate(OssProperties ossProperties) {
        MinioClient minioClient = MinioClient.builder().endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret())
                .region(ossProperties.getRegion()).build();
        return new MinioTemplate(minioClient);
    }

}
