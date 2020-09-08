package com.bheternal.jhome.mybatis.demo.framework.scripting.defaults;

import com.bheternal.jhome.mybatis.demo.framework.builder.ParameterMappingTokenHandler;
import com.bheternal.jhome.mybatis.demo.framework.builder.StaticSqlSource;
import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.SqlSource;
import com.bheternal.jhome.mybatis.demo.framework.parsing.GenericTokenParser;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.DynamicContext;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.SqlNode;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;

/**
 * RawSqlSource
 * 处理无 ${} 和无动态标签的，解析为静态 sqlSource
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.defaults.RawSqlSource
 * @see com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.DynamicSqlSource
 * @see com.bheternal.jhome.mybatis.demo.framework.mapping.SqlSource
 */
public class RawSqlSource implements SqlSource {

    /**
     * StaticSqlSource 存储静态 sqlSource
     */
    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode sqlNode, Class parameterType) {
        // 解析 SqlNode 转为 sql
        this(configuration, getSql(configuration, sqlNode), parameterType);
    }

    /**
     * 处理 #{} 在此处，只处理一次
     *
     * @param configuration
     * @param sql
     * @param parameterType
     * @see org.apache.ibatis.builder.SqlSourceBuilder
     */
    public RawSqlSource(Configuration configuration, String sql, Class parameterType) {
        // 解析 #{}
        // 返回静态 sqlSource
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", tokenHandler::handlerToken);
        this.sqlSource = new StaticSqlSource(configuration, parser.parse(sql));
    }

    private static String getSql(Configuration configuration, SqlNode sqlNode) {
        DynamicContext dynamicContext = new DynamicContext(configuration, null);
        sqlNode.apply(dynamicContext);
        return dynamicContext.getSql();
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

}
