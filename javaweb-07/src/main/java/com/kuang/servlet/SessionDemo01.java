package com.kuang.servlet;

import com.kuang.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        // 得到session
        HttpSession session = req.getSession();
        // 给session中存东西
        session.setAttribute("name",new Person("范曦丹",12));
        // 获取session的id
        String id = session.getId();
        // 判断session是不是新创建的
        if (session.isNew()) {
            resp.getWriter().write("session 创建成功 Id"+id);
        }else{
            resp.getWriter().write("session 已经在服务器中存在了 Id"+id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
