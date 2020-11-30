package com.bheternal.jhome.mybatis.demo.framework3.session.defaults;

import com.bheternal.jhome.mybatis.demo.framework3.executor.Executor;
import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;
import com.bheternal.jhome.mybatis.demo.framework3.session.SqlSession;
import com.bheternal.jhome.mybatis.demo.framework3.session.SqlSessionFactory;

/**
 * DefaultSqlSessionFactory
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.session.defaults.DefaultSqlSessionFactory
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Executor executor = configuration.newExecutor();
        return new DefaultSqlSession(configuration, executor);
    }
}
