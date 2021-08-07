package com.kuang.service.user;

import com.kuang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserService {
    //用户登录
    User login(String userCode, String userPassword);
    //根据用户id修改密码
    boolean updatePwd(int id, String pwd) throws SQLException, ClassNotFoundException;
}
