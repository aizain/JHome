package com.bheternal.jhome.mybatis.demo.framework3.session;

/**
 * SqlSessionFactory
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.session.SqlSessionFactory
 */
public interface SqlSessionFactory {

    /**
     * 打开会话
     *
     * @return
     */
    SqlSession openSession();

}
