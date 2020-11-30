package com.bheternal.jhome.mybatis.demo.framework3.executor;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;

import java.util.List;

/**
 * BatchExecutor
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.executor.BatchExecutor
 */
public class BatchExecutor extends BaseExecutor implements Executor {

    public BatchExecutor(Configuration configuration) {
        super(configuration);
    }

    @Override
    protected <T> List<T> doQuery(MappedStatement ms, Object parameter, BoundSql boundSql) {
        return null;
    }
}
