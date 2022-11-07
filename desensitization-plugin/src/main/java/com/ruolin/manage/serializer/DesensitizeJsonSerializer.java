package com.ruolin.manage.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.ruolin.manage.annotation.Desensitize;
import com.ruolin.manage.enums.InformationType;
import com.ruolin.manage.util.DesensitizeUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Alex Wang
 * Created on 2022/10/28 14:35:15
 */
@AllArgsConstructor
@NoArgsConstructor
public class DesensitizeJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Integer start;

    private Integer maskLength;

    private Boolean keepOriginalLength;

    private InformationType type;

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        String result;
        if (InformationType.EMAIL.equals(type)) {
            // 邮件
            result = DesensitizeUtil.maskEmail(value, start, maskLength, keepOriginalLength);
        } else {
            // 普通
            result = DesensitizeUtil.mask(value, start, maskLength, keepOriginalLength);
        }

        jsonGenerator.writeString(result);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (Objects.nonNull(beanProperty)) {
            // 只处理string类型
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                Desensitize annotation = beanProperty.getAnnotation(Desensitize.class);
                if (Objects.isNull(annotation)) {
                    annotation = beanProperty.getContextAnnotation(Desensitize.class);
                }

                if (Objects.nonNull(annotation)) {
                    // 默认使用注解配置
                    InformationType type = annotation.type();
                    int left = type.getLeft();
                    int maskLength = type.getMaskLength();
                    boolean keepOriginalLength = type.isKeepOriginalLength();

                    boolean override = annotation.isOverride();
                    if (override) {
                        left = annotation.left();
                        maskLength = annotation.maskLength();
                        keepOriginalLength = annotation.keepOriginalLength();
                    }

                    return new DesensitizeJsonSerializer(left, maskLength, keepOriginalLength, type);
                }
                return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
            }
        }
        return NullSerializer.instance;
    }
}
