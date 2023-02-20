package org.grace.luna.config;

import org.grace.luna.template.EmailTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Austin Wong
 * Created on 2022/8/26 16:08:01
 */
@Configuration
@ConditionalOnProperty("spring.mail.host")
public class EmailConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public EmailTemplate emailTemplate() {
        return new EmailTemplate();
    }

}
