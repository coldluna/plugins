package org.grace.luna.selector;

import org.grace.luna.annotation.Strategy;
import org.grace.luna.annotation.StrategyImpl;
import org.grace.luna.principle.Actuator;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alex Wang
 * Created on 2022/11/1 10:07:18
 */
@SuppressWarnings("unchecked")
public class StrategySelector {

    private final Map<Strategy, Actuator<?, ?>> faction;

    public StrategySelector(List<Actuator<?, ?>> actuators) {
        this.faction = actuators.stream().collect(Collectors
                .toMap(actuator -> AnnotationUtils.findAnnotation(actuator.getClass(), Strategy.class),
                        value -> value,
                        (value, anotherValue) -> value));
    }

    public <T, R> Actuator<T, R> selectStrategy(String group, String trait) {
        Strategy strategy = new StrategyImpl(group, trait);
        return (Actuator<T, R>) this.faction.get(strategy);
    }

}
