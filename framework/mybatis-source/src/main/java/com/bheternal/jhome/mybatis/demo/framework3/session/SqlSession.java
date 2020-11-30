package com.bheternal.jhome.mybatis.demo.framework3.session;

import java.util.List;

/**
 * SqlSession
 * Mybatis 会话接口
 *
 * @author Zain
 * @date 2020/9/12
 * @see org.apache.ibatis.session.SqlSession
 */
public interface SqlSession {

    /**
     * 查询
     *
     * @param statementId
     * @param query
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId, Object query);


    /**
     * 查询列表
     *
     * @param statementId
     * @param query
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String statementId, Object query);

}
