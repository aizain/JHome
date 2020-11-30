package com.bheternal.jhome.mybatis.demo.framework3.session.defaults;

import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;
import com.bheternal.jhome.mybatis.demo.framework3.executor.Executor;
import com.bheternal.jhome.mybatis.demo.framework3.session.SqlSession;

import java.util.List;

/**
 * DefaultSqlSession
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.session.defaults.DefaultSqlSession
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statementId, Object query) {
        List<T> list = this.selectList(statementId, query);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> List<T> selectList(String statementId, Object query) {
        MappedStatement statement = configuration.getMappedStatementsById(statementId);
        return executor.query(statement, query);
    }

}
