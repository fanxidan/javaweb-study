package com.kuang.dao.user;

import com.kuang.dao.BaseDao;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//用户的实现类
//改方法用于根据userCode和userPassword来查询数据库中是否有该用户
public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection connection, String userCode, String userPassword) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if (connection!=null){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            System.out.println("输入的密码：" + userPassword);
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            //查询到的结果赋给 user对象
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null,pstm,rs);
//            if (!user.getUserPassword().equals(userPassword)){
//                System.out.println("UserDaoImpl: 未找到用户");
//                user=null;
//            }
        }
        return user;
    }

    //修改当前用户密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null){
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object params[] = {password, id};
            execute = BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResource(null,pstm,null);
        }
        return execute;
    }

}
