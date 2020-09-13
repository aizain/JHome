package com.bheternal.jhome.mybatis.demo.framework3.executor.resultset;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * DefaultResultSetHandler
 *
 * @author Zain
 * @date 2020/9/13
 * @see org.apache.ibatis.executor.resultset.DefaultResultSetHandler
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final Configuration configuration;
    private final MappedStatement mappedStatement;
    private final BoundSql boundSql;

    public DefaultResultSetHandler(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql) {
        this.configuration = configuration;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
    }


    @Override
    public <T> List<T> handlerResultSets(Statement statement) {
        List<T> resultList = new LinkedList<>();
        try {
            Class<?> resultSetType = mappedStatement.getResultSetTypeClass();
            Constructor<?> constructor = resultSetType.getDeclaredConstructor();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                @SuppressWarnings("unchecked")
                T result = (T) constructor.newInstance();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
