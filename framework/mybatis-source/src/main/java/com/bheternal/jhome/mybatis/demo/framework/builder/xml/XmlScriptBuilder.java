package com.bheternal.jhome.mybatis.demo.framework.builder.xml;

import com.bheternal.jhome.mybatis.demo.framework.mapping.SqlSource;
import com.bheternal.jhome.mybatis.demo.framework.scripting.defaults.RawSqlSource;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.DynamicSqlSource;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.IfSqlNode;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.MixedSqlNode;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.SqlNode;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.StaticTextSqlNode;
import com.bheternal.jhome.mybatis.demo.framework.scripting.xmltags.TextSqlNode;
import com.bheternal.jhome.mybatis.demo.framework.session.Configuration;
import org.apache.ibatis.parsing.XNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.List;

/**
 * XmlScriptBuilder
 * 解析 sql 标签脚本的
 *
 * @author Zain
 * @date 2020/9/7
 * @see org.apache.ibatis.scripting.xmltags.XMLScriptBuilder
 */
public class XmlScriptBuilder {

    private final Configuration configuration;
    private final XNode context;
    private final Class<?> parameterType;

    /**
     * 是否为动态标签脚本
     */
    private boolean isDynamic = false;


    public XmlScriptBuilder(Configuration configuration, XNode context, Class<?> parameterType) {
        this.configuration = configuration;
        this.context = context;
        this.parameterType = parameterType;
    }

    public SqlSource parseScriptNode() {
        SqlNode sqlNode = parseDynamicTags(context);
        SqlSource sqlSource;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(configuration, sqlNode);
        } else {
            sqlSource = new RawSqlSource(configuration, sqlNode, parameterType);
        }

        return sqlSource;
    }

    /**
     * 解析动态标签
     *
     * @param context
     * @return
     * @see org.apache.ibatis.scripting.xmltags.XMLScriptBuilder
     */
    private SqlNode parseDynamicTags(XNode context) {
        List<SqlNode> sqlNodes = new LinkedList<>();

        NodeList childNodes = context.getNode().getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            XNode item = context.newXNode(childNodes.item(i));
            if (item.getNode().getNodeType() == Node.TEXT_NODE) {
                String textContent = item.getNode().getTextContent().trim();
                if (textContent.isEmpty()) {
                    continue;
                }
                TextSqlNode textSqlNode = new TextSqlNode(textContent);
                if (textSqlNode.isDynamic()) {
                    sqlNodes.add(textSqlNode);
                    isDynamic = true;
                } else {
                    sqlNodes.add(new StaticTextSqlNode(textContent));
                }

            } else if (item.getNode().getNodeType() == Node.ELEMENT_NODE) {
                isDynamic = true;
                handlerElement(item, sqlNodes);
            }

        }

        return new MixedSqlNode(sqlNodes);
    }

    private void handlerElement(XNode item, List<SqlNode> sqlNodes) {
        if ("if".equals(item.getNode().getNodeName())) {
            SqlNode sqlNode = parseDynamicTags(item);
            IfSqlNode ifSqlNode = new IfSqlNode(item.getStringAttribute("test"), sqlNode);
            sqlNodes.add(ifSqlNode);
        }
    }

}
