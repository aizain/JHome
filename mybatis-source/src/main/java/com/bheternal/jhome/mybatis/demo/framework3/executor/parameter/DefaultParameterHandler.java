package com.bheternal.jhome.mybatis.demo.framework3.executor.parameter;

import com.bheternal.jhome.mybatis.demo.framework.mapping.BoundSql;
import com.bheternal.jhome.mybatis.demo.framework.mapping.MappedStatement;
import com.bheternal.jhome.mybatis.demo.framework.mapping.ParameterMapping;
import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;
import org.apache.ibatis.type.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * DefaultParameterHandler
 *
 * @author Zain
 * @date 2020/9/13
 * @see org.apache.ibatis.scripting.defaults.DefaultParameterHandler
 */
public class DefaultParameterHandler implements ParameterHandler {

    private final Configuration configuration;
    private final MappedStatement mappedStatement;
    private final BoundSql boundSql;
    private final Object parameter;

    public DefaultParameterHandler(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object parameter) {
        this.configuration = configuration;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
        this.parameter = parameter;
    }

    @Override
    public Object getParameter() {
        return parameter;
    }

    @Override
    public void setParameter(PreparedStatement ps) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
