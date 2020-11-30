package com.bheternal.jhome.mybatis.demo.framework3.executor.resultset;

import java.sql.Statement;
import java.util.List;

/**
 * ResultSetHandler
 *
 * @author Zain
 * @date 2020/9/13
 * @see org.apache.ibatis.executor.resultset.ResultSetHandler
 */
public interface ResultSetHandler {

    /**
     * 处理结果集
     *
     * @param statement
     * @param <T>
     * @return
     */
    <T> List<T> handlerResultSets(Statement statement);

}
