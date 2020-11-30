package com.bheternal.jhome.mybatis.demo.framework.session;

import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Configuration
 * mybatis 全局配置类
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.session.Configuration
 */
public class Configuration {

    /**
     * 数据源配置
     */
    private DataSource dataSource;

    /**
     * mappedStatement 配置
     */
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * 配置参数
     */
    private Properties variables = new Properties();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MappedStatement getMappedStatementsById(String id) {
        return mappedStatements.get(id);
    }

    public void addMappedStatements(String mappedStatementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(mappedStatementId, mappedStatement);
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }

    public Properties getVariables() {
        return variables;
    }

    public void setVariables(Properties variables) {
        this.variables = variables;
    }
}
