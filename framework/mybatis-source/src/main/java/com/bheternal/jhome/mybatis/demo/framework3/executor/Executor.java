package com.bheternal.jhome.mybatis.demo.framework3.executor;

import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;

import java.util.List;

/**
 * Executor
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.executor.Executor
 */
public interface Executor {

    /**
     * 执行查询
     *
     * @param ms
     * @param parameter
     * @param <T>
     * @return
     */
    <T> List<T> query(MappedStatement ms, Object parameter);

}
