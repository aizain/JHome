package com.bheternal.jhome.mybatis.demo.framework.mapping;

/**
 * MappedStatement
 * 封装了 statement 标签中的信息
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.mapping.MappedStatement
 */
public class MappedStatement {

    private String id;
    private String statementType;
    private String resultSetType;
    private Class resultSetTypeClass;

    private SqlSource sqlSource;

    public MappedStatement() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public String getResultSetType() {
        return resultSetType;
    }

    public void setResultSetType(String resultSetType) {
        this.resultSetType = resultSetType;
    }

    public Class getResultSetTypeClass() {
        return resultSetTypeClass;
    }

    public void setResultSetTypeClass(Class resultSetTypeClass) {
        this.resultSetTypeClass = resultSetTypeClass;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }
}
