package com.bheternal.jhome.mybatis.mysql.user;

import com.bheternal.jhome.mybatis.mysql.mapper.UserMapper;
import com.bheternal.jhome.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.bheternal.jhome.mybatis.common.MybatisContext.newUser;
import static com.bheternal.jhome.mybatis.common.MybatisContext.sqlSessionFactory;

/**
 * UserMapperTest
 *
 * @author Zain
 * @date 2019-05-16
 */
class UserMapperTest {

    @Test
    void testFindUserById() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findUserById(1);

            Assertions.assertNotNull(user);
            Assertions.assertEquals(user.getId(), 1);
            Assertions.assertEquals(user.getName(), "测试");
        }

    }

    @Test
    void testInsertUser() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int count = userMapper.insertUser(newUser);

            Assertions.assertEquals(1, count);
        }

    }

}