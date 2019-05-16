package com.aizain.jhome.mybatis.source.user;

import com.aizain.jhome.mybatis.source.dao.UserDao;
import com.aizain.jhome.mybatis.source.dao.UserDaoImpl;
import com.aizain.jhome.mybatis.source.po.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.aizain.jhome.mybatis.source.common.MybatisContext.newUser;
import static com.aizain.jhome.mybatis.source.common.MybatisContext.sqlSessionFactory;

/**
 * UserDaoTest
 *
 * @author Zain
 * @date 2019-05-16
 */
class UserDaoTest {

    private UserDao userDao = new UserDaoImpl(sqlSessionFactory);

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