package com.bheternal.jhome.mybatis.mysql.mapper;

import com.bheternal.jhome.mybatis.po.User;

/**
 * UserMapper
 *
 * @author Zain
 * @date 2019-05-16
 */
public interface UserMapper {

    /**
     * 查询用户 根据ID
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

}
