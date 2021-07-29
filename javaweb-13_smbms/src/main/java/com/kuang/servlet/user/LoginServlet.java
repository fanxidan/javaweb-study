package com.kuang.servlet.user;

import com.kuang.pojo.User;
import com.kuang.service.user.UserService;
import com.kuang.service.user.UserServiceImpl;
import com.kuang.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //servelt:控制层，调用业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入LoginServlet---start");
        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //和数据库中用户名密码对比，调用业务层
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);
        if (user != null){//查有此人
            //将用户的信息放到session里面
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            //登录成功后跳转到主页
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            //查无此人，转发会登录页
            req.setAttribute("error","用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
