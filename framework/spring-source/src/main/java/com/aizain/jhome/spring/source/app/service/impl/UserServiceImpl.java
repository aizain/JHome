package com.aizain.jhome.spring.source.app.service.impl;

import com.aizain.jhome.spring.source.app.dao.UserDao;
import com.aizain.jhome.spring.source.app.po.User;
import com.aizain.jhome.spring.source.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserServiceImpl
 *
 * @author Zain
 * @date 2019-05-24
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

}
