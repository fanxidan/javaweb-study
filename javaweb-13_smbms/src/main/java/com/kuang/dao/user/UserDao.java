package com.kuang.dao.user;

import com.kuang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

//dao层登录用户登录的接口
public interface UserDao {
    //得到要登录的用户
    User getLoginUser(Connection connection, String userCode, String userPassword) throws SQLException;

    //修改当前用户的密码
    int updatePwd(Connection connection, int id, String password) throws SQLException;
}
