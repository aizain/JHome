package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

import org.apache.ibatis.scripting.xmltags.ExpressionEvaluator;

/**
 * IfSqlNode
 * if 标签
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.xmltags.IfSqlNode
 * @see SqlNode
 */
public class IfSqlNode implements SqlNode {

    private final ExpressionEvaluator evaluator;
    private final String test;
    private final SqlNode contents;

    public IfSqlNode(String test, SqlNode contents) {
        this.test = test;
        this.contents = contents;
        evaluator = new ExpressionEvaluator();
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        // TODO 使用 OGNL 对 test 内容进行判断
        contents.apply(dynamicContext);
    }

}
