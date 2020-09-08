package com.bheternal.jhome.mybatis.demo.framework.builder.xml;

import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework.mapping.SqlSource;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.type.TypeAliasRegistry;

import java.util.List;

/**
 * XmlMapperBuilder
 * mapper 文件解析
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.builder.xml.XMLMapperBuilder
 */
public class XmlMapperBuilder {

    private final XPathParser parser;
    private final Configuration configuration;

    public XmlMapperBuilder(XPathParser parser, Configuration configuration) {
        this.parser = parser;
        this.configuration = configuration;
    }

    public void parse() throws Exception {
        XNode mapper = parser.evalNode("/mapper");
        String namespace = mapper.getStringAttribute("namespace");

        List<XNode> selectNodes = mapper.evalNodes("select");
        for (XNode selectNode : selectNodes) {
            parseStatement(selectNode, namespace);
        }
    }

    /**
     * 解析 statement
     *
     * @param context
     * @param namespace
     * @see org.apache.ibatis.builder.xml.XMLStatementBuilder
     * @see TypeAliasRegistry
     */
    private void parseStatement(XNode context, String namespace) throws ClassNotFoundException {

        // 解析参数
        String id = namespace + "." + context.getStringAttribute("id");
        String parameterType = context.getStringAttribute("parameterType");
        String resultType = context.getStringAttribute("resultType");
        String statementType = context.getStringAttribute("statementType");

        Class<?> resultTypeClass = resolveType(resultType);
        Class<?> parameterTypeClass = resolveType(parameterType);
        statementType = statementType == null
                ? "prepared"
                : statementType;


        // 解析 sqlSource
        SqlSource sqlSource = createSqlSource(configuration, context, parameterTypeClass);

        // 构建 mappedStatement
        MappedStatement mappedStatement = new MappedStatement();
        mappedStatement.setId(id);
        mappedStatement.setResultSetType(resultType);
        mappedStatement.setResultSetTypeClass(resultTypeClass);
        mappedStatement.setStatementType(statementType);
        mappedStatement.setSqlSource(sqlSource);

        configuration.addMappedStatements(id, mappedStatement);
    }

    /**
     * 解析 sqlSource 对象
     *
     * @param configuration
     * @param context
     * @param parameterType
     * @return
     * @see org.apache.ibatis.scripting.xmltags.XMLLanguageDriver
     * @see org.apache.ibatis.scripting.xmltags.XMLScriptBuilder
     */
    private SqlSource createSqlSource(Configuration configuration, XNode context, Class<?> parameterType) {
        XmlScriptBuilder xmlScriptBuilder = new XmlScriptBuilder(configuration, context, parameterType);
        return xmlScriptBuilder.parseScriptNode();
    }


    /**
     * 可用从已注册的别名中获取类型对象
     * 没有的话走类加载器
     *
     * @param resultType
     * @return
     */
    private Class<?> resolveType(String resultType) {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        return typeAliasRegistry.resolveAlias(resultType);
    }

}
