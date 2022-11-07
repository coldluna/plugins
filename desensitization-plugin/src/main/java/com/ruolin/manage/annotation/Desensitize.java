package com.ruolin.manage.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruolin.manage.enums.InformationType;
import com.ruolin.manage.serializer.DesensitizeJsonSerializer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.ruolin.manage.enums.InformationType.GENERAL;

/**
 * 脱敏注解
 *
 * @author Alex Wang
 * Created on 2022/10/28 15:00:10
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizeJsonSerializer.class)
public @interface Desensitize {

    InformationType type() default GENERAL;

    /**
     * 是否覆盖{@link Desensitize#type()}InformationType的配置值
     *
     * @return
     */
    boolean isOverride() default false;

    /**
     * 左侧保留位数
     */
    int left() default 0;

    /**
     * 需要遮掩的位数
     */
    int maskLength() default 0;

    /**
     * 是否保持与原文本相同长度
     *
     * @return
     */
    boolean keepOriginalLength() default true;
}
