package org.grace.luna.annotation;

import org.grace.luna.validator.IdentityCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.grace.luna.annotation.IP.List;

/**
 * @author Alex Wang
 * Created on 2022/10/31 10:34:22
 */
@Constraint(validatedBy = IdentityCardValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(List.class)
public @interface IP {

    String message() default "请输入正确的IP地址";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @IP} constraints on the same element.
     *
     * @see IP
     */
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        IP[] value();
    }
}
