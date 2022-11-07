package org.grace.luna.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Alex Wang
 * Created on 2022/11/1 10:15:09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Strategy {

    /**
     * group
     *
     * @return
     */
    String group();

    /**
     * 特质
     *
     * @return
     */
    String trait();

}
