package com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags;

/**
 * SqlNode
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.scripting.xmltags.SqlNode
 */
public interface SqlNode {

    void apply(DynamicContext dynamicContext);

}
