package com.bheternal.jhome.mybatis.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * JdbcDemo
 *
 * @author Zain
 * @date 2019-05-15
 */
public class JdbcDemo {

    public static void main(String[] args) throws Exception {
        // 加载驱动
        // Driver.getOSName();
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // System.out.println(System.getProperty("jdbc.drivers"));
        // System.out.println(System.getProperty(String.valueOf(Reflection.getCallerClass())));

        // 获取数据库连接
        Properties jdbcConfig = new Properties();
        jdbcConfig.setProperty("user", "root");
        jdbcConfig.setProperty("password", "fc4fcafd-e86d-4fda-9a96-60147e94cc0f");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jhome?characterEncoding=utf-8",
                jdbcConfig
        );

        // sql预处理
        String testSql = "select * from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(testSql);
        preparedStatement.setString(1, "1");

        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        // 获取执行结果
        while (resultSet.next()) {
            System.out.println("Result" +
                    " id=" + resultSet.getString("name") +
                    " name=" + resultSet.getString("name"));
        }

        preparedStatement.close();
        resultSet.close();
        connection.close();

    }

}
