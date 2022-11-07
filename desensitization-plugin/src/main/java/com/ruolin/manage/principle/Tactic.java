package com.ruolin.manage.principle;

/**
 * @author Alex Wang
 * Created on 2022/10/28 14:53:07
 */
@FunctionalInterface
public interface Tactic<T> {

    /**
     * 声明
     *
     * @return
     */
    T declare();

}
