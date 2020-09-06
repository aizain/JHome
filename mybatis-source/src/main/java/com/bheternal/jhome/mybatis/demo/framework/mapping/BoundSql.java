package com.bheternal.jhome.mybatis.demo.framework.mapping;

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
    private List<ParameterMapping> parameterMappings;

}
