package com.kuang.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Testjdbc3 {
    @Test
    public void test() throws ClassNotFoundException, SQLException {
        //配置信息
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String name = "root";
        String password = "admin";
        Connection connection = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库
            connection = DriverManager.getConnection(url, name, password);
            connection.setAutoCommit(false);
            String sql = "update account set money = money+100 where name = 'a'";
            connection.prepareStatement(sql).executeUpdate();
            int i=1/0;  //制造错误，导致事务提交失败
            String sql2 = "update account set money = money-100 where name = 'a'";
            connection.prepareStatement(sql2).executeUpdate();
            connection.commit();
            System.out.println("提交成功");
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.close();
        }

    }
}
