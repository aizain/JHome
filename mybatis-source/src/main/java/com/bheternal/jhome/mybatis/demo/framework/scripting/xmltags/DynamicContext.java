package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

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

    private final StringJoiner sqlBuilder = new StringJoiner(" ");
    private Map<String, Object> bindings;

}
