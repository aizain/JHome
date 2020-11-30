package com.bheternal.jhome.mybatis.demo.framework3.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Resources
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.io.Resources
 */
public class Resources {

    public static Reader getResourceAsReader(String resource) {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    public static InputStream getResourceAsStream(String resource) {
        return Resources.class.getClassLoader().getResourceAsStream(resource);
    }

}
