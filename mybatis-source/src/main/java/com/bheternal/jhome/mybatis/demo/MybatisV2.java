package com.bheternal.jhome.mybatis.demo;

import com.bheternal.jhome.mybatis.demo.framework.builder.xml.XmlConfigBuilder;
import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework.mapping.ParameterMapping;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.type.SimpleTypeRegistry;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * MybatisV2
 * <p>
 * 1. 配置文件升级为 xml 格式
 * 2. 使用面向过程方式优化代码
 * 3. 使用面向对象思想构造配置类封装对象
 * <p>
 * <p>
 * #{} 相当于jdbc里的占位符 ?
 * ${} 相当于jdbc里的连接符 +
 *
 * @author Zain
 * @date 2020/8/22
 */
public class MybatisV2 {

    private static Configuration configuration;

    public static void main(String[] args) throws Exception {
        MybatisV2 mybatisV2 = new MybatisV2();
        // 1. 加载 xml 全局配置
        mybatisV2.loadXml("v2-mybatis-config.xml");

        // 2. 执行查询
        User query = new User();
        query.setName("测试");
        query.setSex("男");
        List<User> users = mybatisV2.selectList("userMapper.queryUserList", query);
        System.out.printf("users: %s", users);
    }

    private <R, T> List<R> selectList(String statementId, T parameter) throws Exception {
        List<R> result = new LinkedList<>();
        MappedStatement mappedStatement = configuration.getMappedStatementsById(statementId);

        // 1. 获取数据库连接
        Connection connection = getConnection();

        // 2. 获取执行 SQL
        BoundSql boundSql = getBoundSql(mappedStatement, parameter);

        // 3. 创建 statement
        Statement statement = createStatement(mappedStatement, boundSql, connection);

        // 4. 设置参数
        setParameters(parameter, statement, boundSql);

        // 5. 执行 statement
        ResultSet resultSet = executeQuery(statement);

        // 6. 处理结果
        handlerResultSet(resultSet, mappedStatement, result);

        return result;
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

    private ResultSet executeQuery(Statement statement) throws SQLException {
        if (!(statement instanceof PreparedStatement)) {
            return statement.getResultSet();
        }

        PreparedStatement ps = (PreparedStatement) statement;
        return ps.executeQuery();
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

    /**
     * 创建 statement
     *
     * @param mappedStatement
     * @param sql
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

    private BoundSql getBoundSql(MappedStatement mappedStatement, Object parameter) {
        return mappedStatement.getSqlSource().getBoundSql(parameter);
    }

    private Connection getConnection() throws SQLException {
        return configuration.getDataSource().getConnection();
    }


    /**
     * 加载全局配置文件
     * 加载映射文件
     *
     * @param filename xml 解析
     * @see org.apache.ibatis.builder.xml.XMLConfigBuilder
     * @see org.apache.ibatis.parsing.XPathParser
     */
    private void loadXml(String filename) throws Exception {
        configuration = new Configuration();

        // 解析 xml 文件，封装到配置类
        // // 1 获取 xml document 对象
        // Document document = null;
        // try (InputStream is = MybatisV2.class.getClassLoader().getResourceAsStream("demo/" + filename)) {
        //     document = getDocument(is);
        // }

        // 1 获取 xml parser 对象
        XPathParser parser = null;
        try (InputStream is = MybatisV2.class.getClassLoader().getResourceAsStream("demo/" + filename)) {
            parser = getParser(is);
        }

        // 2 解析 xml document 为 configuration
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(parser, configuration);
        xmlConfigBuilder.parseConfiguration(parser.evalNode("/configuration"));
    }


    /**
     * 获取 xml 解析对象
     *
     * @param is
     * @return
     * @see org.apache.ibatis.parsing.XPathParser
     * @see org.apache.ibatis.builder.xml.XMLConfigBuilder
     */
    private XPathParser getParser(InputStream is) {
        return new XPathParser(is);
    }

    /**
     * 获取 xml document 对象
     *
     * @param is
     * @return
     * @throws IOException
     * @throws SAXException
     * @see org.apache.ibatis.parsing.XPathParser
     * @see com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderImpl
     * @deprecated 替换为 getParser
     */
    @Deprecated
    private Document getDocument(InputStream is) throws IOException, SAXException {
        DOMParser domParser = new DOMParser();
        domParser.parse(new InputSource(is));
        return domParser.getDocument();
    }

    public static class User {
        private Long id;
        private String name;
        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

}
