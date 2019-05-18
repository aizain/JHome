package com.aizain.jhome.mybatis.driver.common;

import com.aizain.jhome.mybatis.driver.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * MybatisContext
 *
 * @author Zain
 * @date 2019-05-16
 */
public class MybatisContext {

    public static final String USER_MAPPER_PATH = "com.aizain.jhome.mybatis.source.mapper.UserMapper";
    private static final String NEW_USER_NAME = UUID.randomUUID().toString().replaceAll("-", "");
    public static final User newUser = createUser();
    private static final String CONFIG = "mybatis-config.xml";
    public static final SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();

    private static User createUser() {
        User user = new User();
        user.setName(NEW_USER_NAME);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }

    private static SqlSessionFactory createSqlSessionFactory() {
        try (InputStream inputStream = Resources.getResourceAsStream(CONFIG)) {
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            return new DefaultSqlSessionFactory(new Configuration());
        }
    }

}
