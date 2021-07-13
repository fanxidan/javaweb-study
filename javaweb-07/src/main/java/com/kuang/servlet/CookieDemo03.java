package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        //cookie服务器端从客户端获取
        Cookie[] cookies = req.getCookies();//获取cookie，返回数组，说明cookie存在多个
        if (cookies != null){
            for (Cookie cookie : cookies) {
                //获取cookie的key
                if (cookie.getName().equals("name")) {
                    out.write(URLDecoder.decode(cookie.getValue(),"utf-8"));
                }
            }
        }else{
            out.write("这是第一次访问本网站");
        }
        Cookie cookie = new Cookie("name", URLEncoder.encode("范曦丹","utf-8"));
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
