package com.bheternal.jhome.mybatis.demo.framework3.session;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework3.executor.BatchExecutor;
import com.bheternal.jhome.mybatis.demo.framework3.executor.CachingExecutor;
import com.bheternal.jhome.mybatis.demo.framework3.executor.Executor;
import com.bheternal.jhome.mybatis.demo.framework3.executor.ReuseExecutor;
import com.bheternal.jhome.mybatis.demo.framework3.executor.SimpleExecutor;
import com.bheternal.jhome.mybatis.demo.framework3.executor.parameter.DefaultParameterHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.parameter.ParameterHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.resultset.DefaultResultSetHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.resultset.ResultSetHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.statement.CallableStatementHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.statement.PreparedStatementHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.statement.SimpleStatementHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.statement.StatementHandler;
import org.apache.ibatis.session.ExecutorType;

import java.util.Objects;

/**
 * Configuration
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.session.Configuration
 * @see ExecutorType
 */
public class Configuration extends com.bheternal.jhome.mybatis.demo.framework.session.Configuration {


    public Executor newExecutor() {
        return newExecutor(ExecutorType.SIMPLE);
    }

    public Executor newExecutor(ExecutorType executorType) {
        if (Objects.isNull(executorType)) {
            executorType = ExecutorType.SIMPLE;
        }

        Executor executor;

        switch (executorType) {
            case BATCH:
                executor = new BatchExecutor(this);
                break;
            case REUSE:
                executor = new ReuseExecutor(this);
                break;
            default:
                executor = new SimpleExecutor(this);
                break;
        }

        boolean useCache = true;
        if (useCache) {
            executor = new CachingExecutor(executor);
        }

        return executor;
    }

    /**
     * @param wrapper
     * @param ms
     * @param parameter
     * @param boundSql
     * @return
     * @see org.apache.ibatis.executor.statement.RoutingStatementHandler
     */
    public StatementHandler newStatementHandler(Executor wrapper, MappedStatement ms, Object parameter, BoundSql boundSql) {
        switch (ms.getStatementType()) {
            case "prepared":
                return new PreparedStatementHandler(boundSql, this, parameter, ms, wrapper);
            case "callable":
                return new CallableStatementHandler();
            default:
                return new SimpleStatementHandler();
        }

    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameter, BoundSql boundSql) {
        return new DefaultParameterHandler(this, mappedStatement, boundSql, parameter);
    }

    public ResultSetHandler newResultSetHandler(MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(this, mappedStatement, boundSql);
    }
}
