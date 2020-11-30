package com.bheternal.jhome.mybatis.demo.framework3.executor;

import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;

import java.util.List;

/**
 * CachingExecutor
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.executor.CachingExecutor
 */
public class CachingExecutor implements Executor {

    /**
     * 委托模式, 委托给真正的执行器执行
     */
    private final Executor executor;

    public CachingExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public <T> List<T> query(MappedStatement ms, Object parameter) {
        return executor.query(ms, parameter);
    }
}
