package com.kuang.service.user;

import com.kuang.dao.BaseDao;
import com.kuang.dao.user.UserDao;
import com.kuang.dao.user.UserDaoImpl;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    //业务层都会调用dao层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        try {
            System.out.println("进入UserServiceImpl的login方法：");
            connection = BaseDao.getConnection();
            //通过业务层调用对应具体的数据库操作
            user = userDao.getLoginUser(connection, userCode, userPassword);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    @Override
    public boolean updatePwd(int id, String pwd) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        boolean flag = false;

        //修改密码
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection,id,pwd) > 0) {
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null, null);
        }
        return flag;
    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "1234567");
        System.out.println(admin.getUserPassword());
    }
}
