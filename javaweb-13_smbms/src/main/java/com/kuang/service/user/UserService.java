package com.kuang.service.user;

import com.kuang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //用户登录
    User login(String userCode, String userPassword);
    //根据用户id修改密码
    boolean updatePwd(int id, String pwd) throws SQLException, ClassNotFoundException;
    //查询记录数
    int getUserCount(String username, int userRole) throws SQLException;
    //根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    //
}
