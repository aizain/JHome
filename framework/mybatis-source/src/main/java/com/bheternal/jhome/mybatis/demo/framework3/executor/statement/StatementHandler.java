package com.bheternal.jhome.mybatis.demo.framework3.executor.statement;

import com.bheternal.jhome.mybatis.demo.framework3.executor.parameter.ParameterHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * StatementHandler
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.executor.statement.StatementHandler
 */
public interface StatementHandler {

    /**
     * 查询
     *
     * @param statement
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> List<T> query(Statement statement) throws SQLException;

    /**
     * 获取statement
     *
     * @param connection
     * @return
     */
    Statement prepare(Connection connection);

    /**
     * 参数设置
     *
     * @param statement
     */
    void parameterize(Statement statement);


    /**
     * 获取参数处理器
     *
     * @return
     */
    ParameterHandler getParameterHandler();


}
