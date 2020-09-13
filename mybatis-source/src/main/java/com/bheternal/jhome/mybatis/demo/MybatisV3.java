package com.bheternal.jhome.mybatis.demo;

import com.bheternal.jhome.mybatis.demo.framework3.io.Resources;
import com.bheternal.jhome.mybatis.demo.framework3.session.SqlSession;
import com.bheternal.jhome.mybatis.demo.framework3.session.SqlSessionFactory;
import com.bheternal.jhome.mybatis.demo.framework3.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MybatisV3
 * 1 利用面向对象思想设计框架
 * 2 整理为通用框架，便于其他程序员使用
 *
 * @author Zain
 * @date 2020/9/10
 */
public class MybatisV3 {

    public static void main(String[] args) throws IOException {

        // 构建配置
        String config = "demo/v3-mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 打开会话
        SqlSession sqlSession = sqlSessionFactory.openSession();


        User query = new User();
        query.setName("测试");
        query.setSex("男");
        List<User> users = sqlSession.selectList("userMapper.queryUserList", query);
        System.out.printf("users: %s", users);

    }


    public static class User {
        private Long id;
        private String name;
        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

}
