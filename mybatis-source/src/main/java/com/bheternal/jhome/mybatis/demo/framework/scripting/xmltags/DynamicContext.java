package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * DynamicContext
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.xmltags.DynamicContext
 */
public class DynamicContext {

    public static final String PARAMETER_OBJECT_KEY = "_parameter";
    public static final String DATABASE_ID_KEY = "_databaseId";

    private final StringJoiner sqlBuilder = new StringJoiner(" ");
    private final Map<String, Object> bindings = new HashMap<>();


    /**
     * bindings 传入参数对象以及数据库id
     * 用来处理 #{}
     *
     * @param configuration
     * @param parameterObject
     */
    public DynamicContext(Configuration configuration, Object parameterObject) {
        bindings.put(PARAMETER_OBJECT_KEY, parameterObject);
    }


    public String getSql() {
        return sqlBuilder.toString().trim();
    }

    public void appendSql(String sql) {
        sqlBuilder.add(sql);
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }
}
