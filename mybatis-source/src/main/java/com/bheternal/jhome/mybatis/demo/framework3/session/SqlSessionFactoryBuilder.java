package com.bheternal.jhome.mybatis.demo.framework3.session;

import com.bheternal.jhome.mybatis.demo.framework3.builder.xml.XmlConfigBuilder;
import com.bheternal.jhome.mybatis.demo.framework3.session.defaults.DefaultSqlSessionFactory;

import java.io.InputStream;
import java.io.Reader;

/**
 * SqlSessionFactoryBuilder
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.session.SqlSessionFactoryBuilder
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();
        return build(configuration);
    }

    public SqlSessionFactory build(InputStream inputStream) {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(inputStream);
        Configuration configuration = xmlConfigBuilder.parse();
        return build(configuration);
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }

}
