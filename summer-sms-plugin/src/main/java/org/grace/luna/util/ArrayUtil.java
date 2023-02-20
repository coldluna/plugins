package org.grace.luna.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Artest Wang
 * Created on 2022/9/22 11:17:19
 */
public class ArrayUtil extends cn.hutool.core.util.ArrayUtil {

    /**
     * 将集合转换为数组，可指定类型
     *
     * @param collection
     * @param cast
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(Collection<?> collection, Function<Object, T> cast, Class<T> type) {
        List<T> list = collection.stream().map(cast).collect(Collectors.toList());
        return toArray(list, type);
    }

}
