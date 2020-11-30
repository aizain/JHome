package com.bheternal.jhome.mybatis.demo.framework.builder;

import com.bheternal.jhome.mybatis.demo.framework.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * ParameterMappingTokenHandler
 *
 * @author Zain
 * @date 2020/9/8
 * @see org.apache.ibatis.builder.SqlSourceBuilder
 */
public class ParameterMappingTokenHandler {

    private final List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public ParameterMappingTokenHandler() {
    }

    public String handlerToken(String content) {
        parameterMappingList.add(builderParameterMapping(content));
        return "?";
    }

    private ParameterMapping builderParameterMapping(String content) {
        // TODO 解析 #{} 参数放入 mapping 中
        ParameterMapping parameterMapping = new ParameterMapping();
        parameterMapping.setProperty(content);
        parameterMapping.setJavaType(String.class);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

}
