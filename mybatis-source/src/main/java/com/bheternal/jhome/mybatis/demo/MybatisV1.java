package com.bheternal.jhome.mybatis.demo;

import org.apache.ibatis.type.SimpleTypeRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * MybatisV1
 * V1.0版本:直接改造JDBC代码去完成。
 *
 * @author Zain
 * @date 2020/8/5
 */
public class MybatisV1 {

    private static final Properties prop = new Properties();

    /**
     * 需求: 根据用户信息，查询用户列表
     * <p>
     * 1. 将JDBC中的硬编码，写入properties文件中
     * 2. 封装查询方法为查询列表函数
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // 1 加载配置文件
        loadProperties("mybatis.v1.properties");

        // 2 执行查询
        User query = new User();
        query.setName("测试");
        List<User> users = selectList("queryUserListByName", query);
        System.out.printf("users: %s", users);
    }

    private static <T, R> List<R> selectList(String statementId, T parameter) throws Exception {
        // 1 加载驱动
        Class.forName(prop.getProperty("db.driver"));

        // 2 创建连接
        Connection connection = DriverManager.getConnection(
                prop.getProperty("db.url"),
                prop.getProperty("db.user"),
                prop.getProperty("db.password")
        );

        // 3 SQL 预处理
        PreparedStatement ps = connection.prepareStatement(
                prop.getProperty("db.sql." + statementId)
        );
        // 3.1 获取入参类型
        Class<?> parameterType = Class.forName(prop.getProperty("db.sql." + statementId + ".parameterType"));
        // 3.2 简单类型 org.apache.ibatis.type.SimpleTypeRegistry
        if (SimpleTypeRegistry.isSimpleType(parameterType)) {
            ps.setObject(1, parameter);
        } else {
            // 3.3 对象类型
            // 3.3.1 获取有序的参数, 能和 sql 中的 ? 一一对应
            String[] parameters = prop.getProperty("db.sql." + statementId + ".parameters").split(",");
            // 3.3.2 反射取值, 设置到 ps 中
            for (int i = 0; i < parameters.length; i++) {
                String name = parameters[i];
                Field field = parameterType.getDeclaredField(name);
                field.setAccessible(true);
                Object value = field.get(parameter);
                ps.setObject(i + 1, value);
            }
        }

        // 4 执行 SQL
        ResultSet rs = ps.executeQuery();

        // 5 获取执行结果
        // 5.1 获取结果集类型
        Class<?> resultType = Class.forName(prop.getProperty("db.sql." + statementId + ".resultType"));
        List<R> resultList = new LinkedList<>();
        while (rs.next()) {
            R result = (R) resultType.newInstance();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                String columnName = metaData.getColumnName(i);
                Field field = resultType.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(result, rs.getObject(columnName));
            }
            resultList.add(result);
        }

        // 6 关闭连接
        return resultList;
    }

    private static void loadProperties(String filename) throws IOException {
        InputStream stream = MybatisV1.class.getClassLoader().getResourceAsStream("demo/" + filename);
        prop.load(stream);
    }

    public static class User {
        private Long id;
        private String name;
        private Date create_time;
        private Date update_time;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getCreateTime() {
            return create_time;
        }

        public void setCreateTime(Date createTime) {
            this.create_time = createTime;
        }

        public Date getUpdateTime() {
            return update_time;
        }

        public void setUpdateTime(Date updateTime) {
            this.update_time = updateTime;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", create_time=" + create_time +
                    ", update_time=" + update_time +
                    '}';
        }
    }

}
