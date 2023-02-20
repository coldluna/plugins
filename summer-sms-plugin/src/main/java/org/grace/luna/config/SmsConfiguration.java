package org.grace.luna.config;

import org.grace.luna.properties.SmsProperties;
import org.grace.luna.template.AliSmsTemplate;
import org.grace.luna.template.SmsTemplate;
import org.grace.luna.template.TencentSmsTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Artest Wang
 * Created on 2022/9/21 13:57:22
 */
@Configuration
@EnableConfigurationProperties(SmsProperties.class)
@ConditionalOnProperty("sms.scheme")
public class SmsConfiguration {

    @Bean
    @ConditionalOnProperty(name = "sms.scheme", havingValue = "ALI_CLOUD")
    public SmsTemplate aliSmsTemplate(SmsProperties smsProperties) {
        return new AliSmsTemplate(smsProperties);
    }

    @Bean
    @ConditionalOnProperty(name = "sms.scheme", havingValue = "TENCENT_CLOUD")
    public SmsTemplate tencentSmsTemplate(SmsProperties smsProperties) {
        return new TencentSmsTemplate(smsProperties);
    }

}
