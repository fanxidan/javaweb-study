package com.kuang.servlet;

import com.kuang.pojo.User;
import com.kuang.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户请求，封装成对象
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println(name+password+email);

        User user = new User(name, password, email);

        //使用线程来专门发送邮件
        SendMail sendMail = new SendMail(user);
        //启动线程，启动后会执行run方法来发送邮件
        sendMail.start();

        //注册用户
        req.setAttribute("message","注册成功，我们已经发了一封邮件到您的邮箱");
        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
