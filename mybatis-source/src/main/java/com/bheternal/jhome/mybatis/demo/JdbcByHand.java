package com.bheternal.jhome.mybatis.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * JdbcByHand
 *
 * @author Zain
 * @date 2020/8/20
 */
public class JdbcByHand {

    public static void main(String[] args) throws Exception {

        // 1 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2 获取连接
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "fc4fcafd-e86d-4fda-9a96-60147e94cc0f");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jhome?characterEncoding=utf-8",
                prop
        );

        // 3 SQL 预处理
        PreparedStatement ps = connection.prepareStatement(
                "select * from user where name = ?"
        );
        ps.setString(1, "测试");

        // 4 执行 SQL
        ResultSet rs = ps.executeQuery();

        // 5 获取执行结果
        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            System.out.printf("id: %s, name: %s", id, name);
        }

        // 6 关闭连接
        connection.close();
    }

}
