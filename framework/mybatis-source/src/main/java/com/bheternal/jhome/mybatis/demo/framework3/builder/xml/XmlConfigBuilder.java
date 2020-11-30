package com.bheternal.jhome.mybatis.demo.framework3.builder.xml;

import com.bheternal.jhome.mybatis.demo.framework3.session.Configuration;
import org.apache.ibatis.parsing.XPathParser;

import java.io.InputStream;
import java.io.Reader;

/**
 * XmlConfigBuilder
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.builder.xml.XMLConfigBuilder
 * @see com.bheternal.jhome.mybatis.demo.framework.builder.xml.XmlConfigBuilder
 */
public class XmlConfigBuilder {

    private final XPathParser parser;

    public XmlConfigBuilder(Reader reader) {
        this(new XPathParser(reader));
    }

    public XmlConfigBuilder(InputStream inputStream) {
        this(new XPathParser(inputStream));
    }

    public XmlConfigBuilder(XPathParser parser) {
        this.parser = parser;
    }

    public Configuration parse() {
        Configuration configuration = new Configuration();
        com.bheternal.jhome.mybatis.demo.framework.builder.xml.XmlConfigBuilder v2Builder = new com.bheternal.jhome.mybatis.demo.framework.builder.xml.XmlConfigBuilder(
                parser, configuration
        );
        try {
            v2Builder.parseConfiguration(parser.evalNode("/configuration"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }
}
