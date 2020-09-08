package com.bheternal.jhome.mybatis.demo.framework.mapping;

import java.util.LinkedList;
import java.util.List;

/**
 * BoundSql
 * 封装 sql 语句和 #{} 解析出来参数
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.mapping.BoundSql
 */
public class BoundSql {

    private String sql;
    private List<ParameterMapping> parameterMappings = new LinkedList<>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
