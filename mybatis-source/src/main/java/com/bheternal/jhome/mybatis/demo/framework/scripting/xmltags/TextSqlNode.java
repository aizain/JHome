package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

import com.bheternal.jhome.mybatis.demo.framework.parsing.GenericTokenParser;
import org.apache.ibatis.type.SimpleTypeRegistry;

import java.util.Map;

/**
 * TextSqlNode
 * 带有 ${} 的 sql 文本
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.xmltags.TextSqlNode
 * @see SqlNode
 */
public class TextSqlNode implements SqlNode {

    private final String text;

    public TextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        Map<String, Object> bindings = dynamicContext.getBindings();
        Object parameterObject = bindings.get(DynamicContext.PARAMETER_OBJECT_KEY);
        // 处理 ${}, content 就是 ${} 中的参数名
        GenericTokenParser parser = new GenericTokenParser("${", "}", content -> {
            Object parameter = dynamicContext.getBindings().get(DynamicContext.PARAMETER_OBJECT_KEY);
            if (SimpleTypeRegistry.isSimpleType(parameter.getClass())) {
                return parameter.toString();
            } else {
                // TODO 其他类型处理，使用 OGNL 解析值
            }
            return "";
        });
        dynamicContext.appendSql(text);
    }

    public boolean isDynamic() {
        return text.contains("${");
    }
}
