package com.kuang.jdbc;

import javax.xml.stream.events.StartDocument;
import java.sql.*;

public class Testjdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String name = "root";
        String password = "admin";
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接数据库
        Connection connection = DriverManager.getConnection(url, name, password);
        //向数据库发送SQL的对象
        Statement statement = connection.createStatement();
        //编写sql
        String sql = "select * from users;";
        //执行查询sql，返回结果集
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            System.out.println(resultSet.getObject("id"));
            System.out.println(resultSet.getObject("name"));
            System.out.println(resultSet.getObject("password"));
            System.out.println(resultSet.getObject("email"));
            System.out.println(resultSet.getObject("birthday"));
        }

        //关闭连接，释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
