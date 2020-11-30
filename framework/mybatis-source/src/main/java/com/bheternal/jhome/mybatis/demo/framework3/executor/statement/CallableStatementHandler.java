package com.bheternal.jhome.mybatis.demo.framework3.executor.statement;

import com.bheternal.jhome.mybatis.demo.framework3.executor.parameter.ParameterHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * CallableStatementHandler
 *
 * @author Zain
 * @date 2020/9/13
 * @see org.apache.ibatis.executor.statement.CallableStatementHandler
 */
public class CallableStatementHandler implements StatementHandler {
    @Override
    public <T> List<T> query(Statement statement) throws SQLException {
        return null;
    }

    @Override
    public Statement prepare(Connection connection) {
        return null;
    }

    @Override
    public void parameterize(Statement statement) {

    }

    @Override
    public ParameterHandler getParameterHandler() {
        return null;
    }
}
