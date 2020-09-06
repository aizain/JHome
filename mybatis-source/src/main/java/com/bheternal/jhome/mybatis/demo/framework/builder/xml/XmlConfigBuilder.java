package com.bheternal.jhome.mybatis.demo.framework.builder.xml;

import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * XmlConfigBuilder
 *
 * @author Zain
 * @date 2020/9/6
 * @see org.apache.ibatis.builder.xml.XMLConfigBuilder
 */
public class XmlConfigBuilder {

    private final XPathParser pathParser;
    private final Configuration configuration;

    public XmlConfigBuilder(XPathParser pathParser, Configuration configuration) {
        this.pathParser = pathParser;
        this.configuration = configuration;
    }

    /**
     * 解析为 configuration
     *
     * @param root
     * @see org.apache.ibatis.builder.xml.XMLConfigBuilder
     */
    public void parseConfiguration(XNode root) throws Exception {

        propertiesElement(root.evalNode("properties"));
        environmentsElement(root.evalNode("environments"));
        mapperElement(root.evalNode("mappers"));
    }

    /**
     * 解析 mapper 文件
     *
     * @param mappers
     * @see org.apache.ibatis.builder.xml.XMLMapperBuilder
     */
    private void mapperElement(XNode mappers) throws Exception {
        XNode mapper = mappers.evalNode("mapper");
        String resource = mapper.getStringAttribute("resource");
        try (InputStream is = getClass().getResourceAsStream(resource)) {
            XmlMapperBuilder mapperBuilder = new XmlMapperBuilder(new XPathParser(is), configuration);
            mapperBuilder.parse();
        }

    }


    /**
     * 解析环境变量 数据源
     *
     * @param context
     * @see PooledDataSource
     */
    private void environmentsElement(XNode context) {
        XNode environment = context.evalNode("environment");
        XNode dataSourceNode = environment.evalNode("dataSource");
        String dataSourceType = dataSourceNode.getStringAttribute("type");

        Properties properties = dataSourceNode.getChildrenAsProperties();
        DataSource dataSource = null;
        if ("POOLED".equals(dataSourceType)) {

            DataSourceFactory dataSourceFactory = new PooledDataSourceFactory();
            dataSourceFactory.setProperties(properties);

            dataSource = dataSourceFactory.getDataSource();
        }

        configuration.setDataSource(dataSource);
    }

    /**
     * 解析参数
     *
     * @param context 变量替换解析
     * @see org.apache.ibatis.parsing.PropertyParser
     */
    private void propertiesElement(XNode context) {
        String resource = context.getStringAttribute("resource");
        Properties properties = context.getChildrenAsProperties();
        System.out.printf("读取 properties: %s 配置, %s", resource, properties);
        configuration.getVariables().putAll(properties);

        // 把变量设置到 xml 解析器里面，之后解析的 ${} 都可以替换为变量
        pathParser.setVariables(properties);
    }

}
