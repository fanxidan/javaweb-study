package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

//保存用户上一次访问的时间
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();

        //cookie服务器端从客户端获取
        Cookie[] cookies = req.getCookies();//获取cookie，返回数组，说明cookie存在多个
        if (cookies != null){
            out.write("您上次访问的时间是：");
            for (Cookie cookie : cookies) {
                //获取cookie的key
                if (cookie.getName().equals("lastLoginTime")) {
                    long l = Long.parseLong(cookie.getValue()); //获取cookie的value
                    Date date = new Date(l);
                    out.write(date.toString());
                }
            }
        }else{
            out.write("这是第一次访问本网站");
        }
        //服务端给客户端响应一个cookie
        Cookie cookie = new Cookie("lastLoginTime",System.currentTimeMillis()+"");
        cookie.setMaxAge(24*60*60);//cookie的有效期为一天
        resp.addCookie(cookie);//响应给客户端一个cookie
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
