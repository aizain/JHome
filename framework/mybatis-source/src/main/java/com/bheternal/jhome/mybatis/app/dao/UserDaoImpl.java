package com.bheternal.jhome.mybatis.app.dao;

import com.bheternal.jhome.mybatis.app.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * UserDaoImpl
 *
 * @author Zain
 * @date 2019-05-16
 */
public class UserDaoImpl implements UserDao {

    private static final String USER_MAPPER_PATH = "com.bheternal.jhome.mybatis.app.mapper.UserMapper";

    private final SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(Integer id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne(
                    USER_MAPPER_PATH + ".findUserById",
                    1
            );
        }
    }

    @Override
    public User insertUser(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.insert(
                    USER_MAPPER_PATH + ".insertUser",
                    user
            );
            sqlSession.commit();
        }
        return user;
    }
}
