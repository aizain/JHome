package com.bheternal.jhome.mybatis.user;

import com.bheternal.jhome.mybatis.app.dao.UserDao;
import com.bheternal.jhome.mybatis.app.dao.UserDaoImpl;
import com.bheternal.jhome.mybatis.app.po.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.bheternal.jhome.mybatis.common.MybatisContext.newUser;
import static com.bheternal.jhome.mybatis.common.MybatisContext.sqlSessionFactory;

/**
 * UserDaoTest
 *
 * @author Zain
 * @date 2019-05-16
 */
class UserDaoTest {

    private final UserDao userDao = new UserDaoImpl(sqlSessionFactory);

    @Test
    void testFindUserById() {
        User user = userDao.findUserById(1);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), 1);
        Assertions.assertEquals(user.getName(), "测试");
    }

    @Test
    void testInsertUser() {
        User user = userDao.insertUser(newUser);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
    }

}