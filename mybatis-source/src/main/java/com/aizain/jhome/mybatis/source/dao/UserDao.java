package com.aizain.jhome.mybatis.source.dao;

import com.aizain.jhome.mybatis.source.po.User;

/**
 * UserDao
 *
 * @author Zain
 * @date 2019-05-16
 */
public interface UserDao {

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
    User insertUser(User user);

}
