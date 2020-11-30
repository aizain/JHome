package com.bheternal.jhome.mybatis.user;

import com.bheternal.jhome.mybatis.app.mapper.UserAnnotationMapper;
import com.bheternal.jhome.mybatis.app.po.OrderWithUser;
import com.bheternal.jhome.mybatis.app.po.User;
import com.bheternal.jhome.mybatis.app.vo.UserQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bheternal.jhome.mybatis.common.MybatisContext.newUser;
import static com.bheternal.jhome.mybatis.common.MybatisContext.sqlSessionFactory;

/**
 * UserMapperTest
 *
 * @author Zain
 * @date 2019-05-23
 */
@Slf4j
class UserAnnotationMapperTest {

    @Test
    void testFindUserById() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserAnnotationMapper userAnnotationMapper = sqlSession.getMapper(UserAnnotationMapper.class);
            User user = userAnnotationMapper.findUserById(1);

            Assertions.assertNotNull(user);
            Assertions.assertEquals(1, user.getId());
        }

    }

    @Test
    void testInsertUser() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserAnnotationMapper userAnnotationMapper = sqlSession.getMapper(UserAnnotationMapper.class);
            Integer number = userAnnotationMapper.insertUser(newUser);

            Assertions.assertEquals(1, number);

            User user = userAnnotationMapper.findUserById(newUser.getId());
            Assertions.assertNotNull(user);
            Assertions.assertEquals(newUser.getId(), user.getId());
        }

    }

    @Test
    void testFindUserByVo() {
        User user = new User();
        user.setName("测试");
        UserQueryVo vo = new UserQueryVo();
        vo.setUser(user);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserAnnotationMapper userAnnotationMapper = sqlSession.getMapper(UserAnnotationMapper.class);
            List<User> users = userAnnotationMapper.findUserByVo(vo);

            Assertions.assertNotNull(users);
            Assertions.assertTrue(users.size() >= 1);
            users.forEach(v -> log.debug("Test {}", v.toString()));
        }

    }

    @Test
    void testFindUserByIdWithMap() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserAnnotationMapper userAnnotationMapper = sqlSession.getMapper(UserAnnotationMapper.class);
            User user = userAnnotationMapper.findUserByIdWithMap(1);

            Assertions.assertNotNull(user);
            Assertions.assertNotNull(user.getCreateTime());
            Assertions.assertEquals(1, user.getId());
        }

    }

    @Test
    void testFindOrderLazyLoadUser() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserAnnotationMapper userAnnotationMapper = sqlSession.getMapper(UserAnnotationMapper.class);
            List<OrderWithUser> orderWithUsers = userAnnotationMapper.findOrderLazyLoadUser();

            Assertions.assertNotNull(orderWithUsers);
            Assertions.assertTrue(orderWithUsers.size() >= 1);

            for (OrderWithUser orderWithUser : orderWithUsers) {
                // debug可能看不出来区别，需要日志观察
                // 开启侵入式延迟加载时 aggressiveLazyLoading true, 这步之前就会查询sql
                log.debug("Order get id {}", orderWithUser.getId());
                // 开启深度延迟加载时 aggressiveLazyLoading false, 这步之前才会查询sql
                log.debug("Order get user {}", orderWithUser.getUser());
            }

        }

    }

}