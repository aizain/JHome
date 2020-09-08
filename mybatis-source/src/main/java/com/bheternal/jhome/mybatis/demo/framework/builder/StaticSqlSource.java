package com.bheternal.jhome.mybatis.demo.framework.builder;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.ParameterMapping;
import com.bheternal.jhome.mybatis.demo.framework.mapping.SqlSource;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;

import java.util.List;

/**
 * StaticSqlSource
 * 存储静态的 sql 及参数
 *
 * @author Zain
 * @date 2020/9/7
 * @see org.apache.ibatis.builder.StaticSqlSource
 */
public class StaticSqlSource implements SqlSource {

    private final Configuration configuration;
    private final String sql;
    private final List<ParameterMapping> parameterMappings;

    public StaticSqlSource(Configuration configuration, String sql) {
        this.configuration = configuration;
        this.sql = sql;
        parameterMappings = null;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        BoundSql boundSql = new BoundSql();
        boundSql.setParameterMappings(parameterMappings);
        boundSql.setSql(sql);
        return boundSql;
    }

}
