package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

/**
 * StaticTextSqlNode
 * 不带 ${}
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.xmltags.StaticTextSqlNode
 * @see SqlNode
 */
public class StaticTextSqlNode implements SqlNode {

    private final String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        dynamicContext.appendSql(text);
    }
}
