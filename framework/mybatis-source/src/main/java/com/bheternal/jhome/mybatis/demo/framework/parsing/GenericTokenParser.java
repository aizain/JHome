package com.bheternal.jhome.mybatis.demo.framework.parsing;

import org.apache.ibatis.parsing.TokenHandler;

/**
 * GenericTokenParser
 *
 * @author Zain
 * @date 2020/9/8
 * @see org.apache.ibatis.parsing.GenericTokenParser
 */
public class GenericTokenParser extends org.apache.ibatis.parsing.GenericTokenParser {
    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        super(openToken, closeToken, handler);
    }
}
