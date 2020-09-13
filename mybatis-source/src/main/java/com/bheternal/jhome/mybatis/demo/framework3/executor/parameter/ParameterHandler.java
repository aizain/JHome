package com.bheternal.jhome.mybatis.demo.framework3.executor.parameter;

import java.sql.PreparedStatement;

/**
 * ParameterHandler
 *
 * @author Zain
 * @date 2020/9/13
 * @see org.apache.ibatis.executor.parameter.ParameterHandler
 */
public interface ParameterHandler {

    /**
     * 获取参数
     *
     * @return
     */
    Object getParameter();

    /**
     * 设置参数
     *
     * @param statement
     */
    void setParameter(PreparedStatement statement);

}
