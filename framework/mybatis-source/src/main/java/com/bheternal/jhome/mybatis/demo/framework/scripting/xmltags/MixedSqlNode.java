package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

import java.util.List;

/**
 * MixedSqlNode
 * 一组同级节点
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.xmltags.MixedSqlNode
 * @see SqlNode
 */
public class MixedSqlNode implements SqlNode {

    private final List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        contents.forEach(sqlNode -> sqlNode.apply(dynamicContext));
    }
}
