package org.grace.luna.config;

import org.grace.luna.principle.Actuator;
import org.grace.luna.selector.StrategySelector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Alex Wang
 * Created on 2022/11/1 10:46:30
 */
@Configuration
public class StrategyConfiguration {

    @Bean
    @ConditionalOnBean(Actuator.class)
    @ConditionalOnMissingBean
    public StrategySelector strategySelector(List<Actuator<?, ?>> actuators) {
        return new StrategySelector(actuators);
    }

}
