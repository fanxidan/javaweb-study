package com.kuang.jdbc;

import java.sql.*;

public class Testjdbc2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String name = "root";
        String password = "admin";
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接数据库
        Connection connection = DriverManager.getConnection(url, name, password);
        //编写sql
        String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
        //预编译
        PreparedStatement preparedStatement  = connection.prepareStatement(sql);
        preparedStatement.setInt(1,3);//给第一个占位符？赋值为1
        preparedStatement.setString(2, "fanxd2");//给第一个占位符？赋值为1
        preparedStatement.setString(3,"2345");//给第一个占位符？赋值为1
        preparedStatement.setString(4,"c@qq.com");//给第一个占位符？赋值为1
        preparedStatement.setString(5, "2020-12-12");//给第一个占位符？赋值为1
        //执行
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
        //关闭连接，释放资源
        preparedStatement.close();
        connection.close();
    }
}
