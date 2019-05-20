package com.aizain.jhome.mybatis.source.user;

import com.aizain.jhome.mybatis.source.mapper.UserMapper;
import com.aizain.jhome.mybatis.source.po.OrderWithUser;
import com.aizain.jhome.mybatis.source.po.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.aizain.jhome.mybatis.source.common.MybatisContext.newUser;
import static com.aizain.jhome.mybatis.source.common.MybatisContext.sqlSessionFactory;

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

    @Test
    void testFindUserByIdWithAlias() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findUserByIdWithAlias(1);

            Assertions.assertNotNull(user);
            Assertions.assertEquals(user.getId(), 1);
            Assertions.assertEquals(user.getName(), "测试");
        }

    }

    @Test
    void testFindUserAndOrder() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.findUserAndOrder();

            Assertions.assertNotNull(users);
            Assertions.assertTrue(users.size() >= 1);

            System.out.println(users);
        }

    }

    @Test
    void testFindOrderAndUser() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<OrderWithUser> orders = userMapper.findOrderAndUser();

            Assertions.assertNotNull(orders);
            Assertions.assertTrue(orders.size() >= 1);

            orders.forEach(System.out::println);
        }

    }

}