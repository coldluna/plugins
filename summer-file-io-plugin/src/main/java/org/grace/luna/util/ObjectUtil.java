package org.grace.luna.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author Austin Wong
 * @version 1.0.0
 * @since JDK1.8
 * Created on 2022/5/19 16:18:28
 */
@Slf4j
public class ObjectUtil extends cn.hutool.core.util.ObjectUtil {

    public static boolean worthless(Object object) {
        boolean isNull = true;
        if (Objects.isNull(object)) {
            return true;
        }

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                if (!ObjectUtils.isEmpty(value)) {
                    isNull = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                log.error("发生非法访问异常", e);
            }
        }
        return isNull;
    }

}
