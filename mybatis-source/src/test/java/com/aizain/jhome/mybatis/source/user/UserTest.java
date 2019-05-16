package com.aizain.jhome.mybatis.source.user;

import com.aizain.jhome.mybatis.source.po.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.aizain.jhome.mybatis.source.common.MybatisContext.*;


/**
 * UserTest
 *
 * @author Zain
 * @date 2019-05-16
 */
class UserTest {


    @Test
    void testFindUserById() {

        User expected = new User();
        expected.setId(1);
        expected.setName("测试");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        User actual = sqlSession.selectOne(
                USER_MAPPER_PATH + ".findUserById",
                1
        );
        sqlSession.close();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());

        System.out.println(actual);
    }

    @Test
    void testFindUserListLikeName() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> actual = sqlSession.selectList(
                USER_MAPPER_PATH + ".findUserListLikeName",
                "测试"
        );
        sqlSession.close();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());

        System.out.println(actual);
    }

    @Test
    void testFindUserListLikeNameSafe() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> actual = sqlSession.selectList(
                USER_MAPPER_PATH + ".findUserListLikeNameSafe",
                "测试"
        );
        sqlSession.close();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());

        System.out.println(actual);
    }

    @Test
    void testInsertUser() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        int actual = sqlSession.insert(
                USER_MAPPER_PATH + ".insertUser",
                newUser
        );
        sqlSession.commit();
        sqlSession.close();

        Assertions.assertEquals(1, actual);
        Assertions.assertNotNull(newUser.getId());

        System.out.println(newUser);
    }


    @Test
    void testUpdateUserById() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        newUser.setName("update");

        sqlSession.insert(
                USER_MAPPER_PATH + ".insertUser",
                newUser
        );
        sqlSession.update(
                USER_MAPPER_PATH + ".updateUserById",
                newUser
        );
        User actual = sqlSession.selectOne(
                USER_MAPPER_PATH + ".findUserById",
                newUser.getId()
        );
        sqlSession.commit();
        sqlSession.close();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(newUser.getId(), actual.getId());
        Assertions.assertEquals(newUser.getName(), actual.getName());

        System.out.println(actual);
    }

    @Test
    void testDeleteUserById() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert(
                USER_MAPPER_PATH + ".insertUser",
                newUser
        );
        int actual = sqlSession.delete(
                USER_MAPPER_PATH + ".deleteUserById",
                newUser.getId()
        );
        sqlSession.commit();
        sqlSession.close();

        Assertions.assertEquals(1, actual);

        System.out.println(actual);
    }


}