package com.aizain.jhome.design.pattern.factory.abstractz;

/**
 * AbstractFactory
 *
 * @author Zain
 * @date 2019-07-13
 */
public interface AbstractFactory<R> {

    /**
     * 创建实例
     *
     * @return
     */
    R create();

}