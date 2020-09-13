package com.bheternal.jhome.mybatis.demo.framework3.executor;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;

import java.util.List;

/**
 * ReuseExecutor
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.executor.ReuseExecutor
 */
public class ReuseExecutor extends BaseExecutor implements Executor {

    public ReuseExecutor(Configuration configuration) {
        super(configuration);
    }

    @Override
    protected <T> List<T> doQuery(MappedStatement ms, Object parameter, BoundSql boundSql) {
        return null;
    }
}
