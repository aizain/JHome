package com.bheternal.jhome.mybatis.demo.framework3.builder.xml;

import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;

/**
 * XmlStatementBuilder
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.builder.xml.XMLStatementBuilder
 * @see com.bheternal.jhome.mybatis.demo.framework.builder.xml.XmlMapperBuilder
 */
public class XmlStatementBuilder {

    private final Configuration configuration;

    public XmlStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

}
