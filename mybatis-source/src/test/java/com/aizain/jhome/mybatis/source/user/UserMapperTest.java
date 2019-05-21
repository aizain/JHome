package com.aizain.jhome.mybatis.source.user;

import com.aizain.jhome.mybatis.source.mapper.UserMapper;
import com.aizain.jhome.mybatis.source.po.OrderWithUser;
import com.aizain.jhome.mybatis.source.po.User;
import com.aizain.jhome.mybatis.source.vo.UserQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.aizain.jhome.mybatis.source.common.MybatisContext.newUser;
import static com.aizain.jhome.mybatis.source.common.MybatisContext.sqlSessionFactory;

/**
 * UserMapperTest
 *
 * @author Zain
 * @date 2019-05-16
 */
@Slf4j
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

            log.debug("Test {}", Arrays.toString(users.toArray()));
        }

    }

    @Test
    void testFindOrderAndUser() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<OrderWithUser> orders = userMapper.findOrderAndUser();

            Assertions.assertNotNull(orders);
            Assertions.assertTrue(orders.size() >= 1);

            orders.forEach(order -> log.debug("Test {}", order.toString()));
        }

    }

    @Test
    void testFindUserByVo() {

        User user = new User();
        user.setName("测试");
        UserQueryVo vo = new UserQueryVo();
        vo.setUser(user);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // test if
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> orders = userMapper.findUserByVo(vo);

            Assertions.assertNotNull(orders);
            Assertions.assertTrue(orders.size() >= 1);
            orders.forEach(order -> log.debug("Test {}", order.toString()));

            user.setName(null);
            user.setId(1);

            orders = userMapper.findUserByVo(vo);

            Assertions.assertNotNull(orders);
            Assertions.assertEquals(1, orders.size());
            orders.forEach(order -> log.debug("Test {}", order.toString()));

            // test foreach
            vo.setUser(null);
            vo.setIds(Arrays.asList(1, 2));

            orders = userMapper.findUserByVo(vo);

            Assertions.assertNotNull(orders);
            Assertions.assertEquals(2, orders.size());
            orders.forEach(order -> log.debug("Test {}", order.toString()));
        }

    }

    @Test
    void testCountUserByVo() {

        User user = new User();
        user.setName("测试");
        UserQueryVo vo = new UserQueryVo();
        vo.setUser(user);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Integer number = userMapper.countUserByVo(vo);

            Assertions.assertNotNull(number);
            Assertions.assertTrue(number >= 1);
            log.debug("Test {}", number.toString());
        }

    }

    @Test
    void testFindUserByIds() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> orders = userMapper.findUserByIds(Arrays.asList(1, 2));

            Assertions.assertNotNull(orders);
            Assertions.assertEquals(2, orders.size());
            orders.forEach(order -> log.debug("Test {}", order.toString()));
        }

    }

}