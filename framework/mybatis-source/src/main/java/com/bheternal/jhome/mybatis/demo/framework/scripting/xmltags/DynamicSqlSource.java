package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

import com.bheternal.jhome.mybatis.demo.framework.builder.ParameterMappingTokenHandler;
import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.SqlSource;
import com.bheternal.jhome.mybatis.demo.framework.parsing.GenericTokenParser;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;

/**
 * DynamicSqlSource
 * <p>
 * 处理 ${} 和动态标签
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.xmltags.DynamicSqlSource
 */
public class DynamicSqlSource implements SqlSource {

    private final Configuration configuration;
    private final SqlNode rootSqlNode;

    public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {
        this.configuration = configuration;
        this.rootSqlNode = rootSqlNode;
    }


    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        DynamicContext dynamicContext = new DynamicContext(configuration, parameterObject);
        rootSqlNode.apply(dynamicContext);

        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", tokenHandler::handlerToken);

        String sql = parser.parse(dynamicContext.getSql());
        BoundSql boundSql = new BoundSql();
        boundSql.setSql(sql);
        boundSql.setParameterMappings(tokenHandler.getParameterMappingList());
        return boundSql;
    }

}
