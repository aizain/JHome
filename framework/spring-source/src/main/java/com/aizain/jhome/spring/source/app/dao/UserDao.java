package com.aizain.jhome.spring.source.app.dao;

import com.aizain.jhome.spring.source.app.po.User;

/**
 * UserDao
 *
 * @author Zain
 * @date 2019-05-24
 */
public interface UserDao {

    /**
     * 查询用户 根据id
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

}
