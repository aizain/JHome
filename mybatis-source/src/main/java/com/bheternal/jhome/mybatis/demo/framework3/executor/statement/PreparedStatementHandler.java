package com.bheternal.jhome.mybatis.demo.framework3.executor.statement;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework3.executor.Executor;
import com.bheternal.jhome.mybatis.demo.framework3.executor.parameter.ParameterHandler;
import com.bheternal.jhome.mybatis.demo.framework3.executor.resultset.ResultSetHandler;
import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * PreparedStatementHandler
 *
 * @author Zain
 * @date 2020/9/13
 * @see org.apache.ibatis.executor.statement.PreparedStatementHandler
 * @see org.apache.ibatis.executor.statement.BaseStatementHandler
 */
public class PreparedStatementHandler implements StatementHandler {

    private final BoundSql boundSql;
    private final Configuration configuration;
    private final ParameterHandler parameterHandler;
    private final ResultSetHandler resultSetHandler;
    private final MappedStatement mappedStatement;
    private final Executor executor;

    public PreparedStatementHandler(BoundSql boundSql, Configuration configuration, Object parameter, MappedStatement mappedStatement, Executor executor) {
        this.boundSql = boundSql;
        this.configuration = configuration;
        this.parameterHandler = configuration.newParameterHandler(mappedStatement, parameter, boundSql);
        this.mappedStatement = mappedStatement;
        this.executor = executor;
        resultSetHandler = configuration.newResultSetHandler(mappedStatement, boundSql);
    }

    @Override
    public <T> List<T> query(Statement statement) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.handlerResultSets(ps);
    }

    @Override
    public Statement prepare(Connection connection) {
        try {
            return connection.prepareStatement(boundSql.getSql());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void parameterize(Statement statement) {
        parameterHandler.setParameter((PreparedStatement) statement);
    }

    @Override
    public ParameterHandler getParameterHandler() {
        return parameterHandler;
    }
}
