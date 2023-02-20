package org.grace.luna.principle;

/**
 * 执行者接口
 *
 * @author Austin Wong
 * @version 1.0.0
 * @since JDK1.8
 * Created on 2022/8/16 14:46:07
 */
@FunctionalInterface
public interface Actuator<T, R> {

    /**
     * 执行业务逻辑处理
     *
     * @param t 需要处理的实体
     * @return
     */
    R execute(T t);

}
