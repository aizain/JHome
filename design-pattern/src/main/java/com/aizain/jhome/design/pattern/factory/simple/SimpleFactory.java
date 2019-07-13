package com.aizain.jhome.design.pattern.factory.simple;

/**
 * SimpleFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public interface SimpleFactory<T, R> {

    /**
     * 创建实例
     *
     * @param object
     * @return
     */
    R create(T object);

}
