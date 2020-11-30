package com.aizain.jhome.spring.source.app.service;

import com.aizain.jhome.spring.source.app.po.User;

/**
 * UserService
 *
 * @author Zain
 * @date 2019-05-24
 */
public interface UserService {

    /**
     * 查询用户 根据id
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

}
