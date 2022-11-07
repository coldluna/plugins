package com.ruolin.manage.config;

import com.ruolin.manage.template.EmailTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Austin Wong
 * @version 1.0.0
 * @since JDK1.8
 * Created on 2022/8/26 16:08:01
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.mail")
public class EmailConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public EmailTemplate emailTemplate() {
        return new EmailTemplate();
    }

}
