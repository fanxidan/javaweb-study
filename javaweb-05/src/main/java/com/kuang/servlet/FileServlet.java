package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String realPath = "C:\\Users\\王金龙\\IdeaProjects\\javaweb-study\\javaweb-05\\target\\javaweb-05\\WEB-INF\\1.jpg";
        System.out.println(realPath);
        String filename = realPath.substring(realPath.lastIndexOf("\\" + 1));
        resp.setHeader("Content-Disposition","attachment;filename="+filename);
        FileInputStream inputStream = new FileInputStream(realPath);
        byte[] buffers = new byte[1024];
        ServletOutputStream outputStream = resp.getOutputStream();
        while ((inputStream.read(buffers))>0) {
            outputStream.write(buffers);
        }
        outputStream.close();
        inputStream.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
