package com.bheternal.jhome.mybatis.demo.framework.mapping;

/**
 * ParameterMapping
 * <p>
 * 封装了 #{} 解析出来的参数名称、类型
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.mapping.ParameterMapping
 * @see org.apache.ibatis.mapping.ParameterMap
 */
public class ParameterMapping {

    private String property;
    private Class<?> javaType;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }
}

