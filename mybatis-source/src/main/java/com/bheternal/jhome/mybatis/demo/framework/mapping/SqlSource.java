package com.bheternal.jhome.mybatis.demo.framework.mapping;

/**
 * SqlSource
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.mapping.SqlSource
 */
public interface SqlSource {

    /**
     * 基于参数，获取用于执行的 SQL 语句
     *
     * @param parameterObject
     * @return
     */
    BoundSql getBoundSql(Object parameterObject);

}
