package com.bheternal.jhome.mybatis.demo.framework3.executor;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework.mapping.ParameterMapping;
import com.bheternal.jhome.mybatis.demo.framework3.executor.statement.StatementHandler;
import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;
import org.apache.ibatis.type.SimpleTypeRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * SimpleExecutor
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.executor.SimpleExecutor
 */
public class SimpleExecutor extends BaseExecutor implements Executor {

    public SimpleExecutor(Configuration configuration) {
        super(configuration);
    }


    /**
     * 支持简单查询
     *
     * @param ms
     * @param parameter
     * @param boundSql
     * @param <T>
     * @return
     * @see org.apache.ibatis.session.Configuration
     * @see org.apache.ibatis.executor.statement.StatementHandler
     */
    @Override
    protected <T> List<T> doQuery(MappedStatement ms, Object parameter, BoundSql boundSql) {

        StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, boundSql);

        try {
            Statement statement = prepareStatement(handler);
            return handler.query(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Statement prepareStatement(StatementHandler handler) throws SQLException {
        Connection connection = configuration.getDataSource().getConnection();
        Statement statement = handler.prepare(connection);
        handler.parameterize(statement);
        return statement;
    }


    // ******************* old ********************* //

    protected <T> List<T> oldDoQuery(MappedStatement ms, Object parameter, BoundSql boundSql) {
        List<T> result = new LinkedList<>();
        // 1. 获取数据库连接
        try (Connection connection = getConnection()) {

            // 3. 创建 statement
            Statement statement = createStatement(ms, boundSql, connection);

            // 4. 设置参数
            setParameters(parameter, statement, boundSql);

            // 5. 执行 statement
            ResultSet resultSet = executeQuery(statement);

            // 6. 处理结果
            handlerResultSet(resultSet, ms, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private Connection getConnection() throws SQLException {
        return configuration.getDataSource().getConnection();
    }

    private ResultSet executeQuery(Statement statement) throws SQLException {
        if (!(statement instanceof PreparedStatement)) {
            return statement.getResultSet();
        }

        PreparedStatement ps = (PreparedStatement) statement;
        return ps.executeQuery();
    }

    /**
     * 创建 statement
     *
     * @param mappedStatement
     * @param boundSql
     * @param connection
     * @return
     * @throws SQLException router
     * @see org.apache.ibatis.executor.statement.RoutingStatementHandler
     * <p>
     * handler
     * @see org.apache.ibatis.executor.statement.SimpleStatementHandler
     * @see org.apache.ibatis.executor.statement.PreparedStatementHandler
     * @see org.apache.ibatis.executor.statement.CallableStatementHandler
     */
    private Statement createStatement(MappedStatement mappedStatement, BoundSql boundSql, Connection connection) throws SQLException {
        String statementType = mappedStatement.getStatementType();
        switch (statementType) {
            case "prepared":
                return connection.prepareStatement(boundSql.getSql());
            case "callable":
                return connection.prepareCall(boundSql.getSql());
            default:
                return connection.createStatement();
        }
    }

    /**
     * 设置 statement 参数
     *
     * @param parameter
     * @param statement
     * @param boundSql
     * @param <T>       parameter handler
     * @see org.apache.ibatis.executor.parameter.ParameterHandler
     * @see org.apache.ibatis.scripting.defaults.DefaultParameterHandler
     * <p>
     * type handler
     * @see org.apache.ibatis.type.BaseTypeHandler
     */
    private <T> void setParameters(T parameter, Statement statement, BoundSql boundSql) throws SQLException, NoSuchFieldException, IllegalAccessException {
        if (!(statement instanceof PreparedStatement)) {
            return;
        }

        PreparedStatement ps = (PreparedStatement) statement;
        Class<?> parameterType = parameter.getClass();

        // 简单类型直接赋值
        if (SimpleTypeRegistry.isSimpleType(parameterType)) {
            ps.setObject(1, parameter);
        } else {

            // 解析参数列表
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                String name = parameterMapping.getProperty();
                Field field = parameterType.getDeclaredField(name);
                field.setAccessible(true);
                Object value = field.get(parameter);
                ps.setObject(i + 1, value);
            }
        }

    }

    private <R> void handlerResultSet(ResultSet rs, MappedStatement mappedStatement, List<R> resultList) throws Exception {
        Class<?> resultSetType = mappedStatement.getResultSetTypeClass();
        Constructor<?> constructor = resultSetType.getDeclaredConstructor();

        while (rs.next()) {
            @SuppressWarnings("unchecked")
            R result = (R) constructor.newInstance();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                String columnName = metaData.getColumnName(i);
                try {
                    Field field = resultSetType.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(result, rs.getObject(columnName));
                } catch (NoSuchFieldException e) {
                    // ignore
                }

            }
            resultList.add(result);
        }

    }

}
