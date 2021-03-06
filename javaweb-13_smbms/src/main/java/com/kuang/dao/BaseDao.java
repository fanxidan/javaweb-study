package com.kuang.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类：创建连接池，查询，释放资源，关闭连接池
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，类加载的时候初始化
    static {
        //通过类加载器读取对应的资源
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            //读取输入流
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    //获取数据库的连接
    public static Connection getConnection(){
        //初始化给定的类。而我们给定的 MySQL 的 Driver 类中，它在静态代码块中通过 JDBC 的 DriverManager 注册了一下驱动
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //编写查询公共类
    public static ResultSet execute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, String sql,Object[] params) throws SQLException {
        //预编译的sql在后面直接执行就行
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始的
            preparedStatement.setObject(i + 1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    //编写增删改的公共方法
    public static int execute(Connection connection,PreparedStatement preparedStatement,String sql,Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始的
            preparedStatement.setObject(i + 1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    //关闭连接释放资源
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet) {
        boolean flag = true;
        if (resultSet != null){
            try {
                resultSet.close();
                //gc回收
                resultSet = null;
            } catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
                //gc回收
                preparedStatement = null;
            } catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection != null){
            try {
                connection.close();
                //gc回收
                connection = null;
            } catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
