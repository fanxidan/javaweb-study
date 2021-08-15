package com.kuang.dao.user;

import com.kuang.pojo.Role;
import com.kuang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//dao层登录用户登录的接口
public interface UserDao {
    //得到要登录的用户
    User getLoginUser(Connection connection, String userCode, String userPassword) throws SQLException;

    //修改当前用户的密码
    int updatePwd(Connection connection, int id, String password) throws SQLException;

    //根据用户名或者角色查询用户总数
    int getUserCount(Connection connection,String username,int userRole) throws SQLException;

    //获取用户列表
    List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException;

}
