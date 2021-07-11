package com.kuang.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //浏览器每4秒刷新一次
        resp.setHeader("refresh","4");
        //在内存中建立一个图片
        BufferedImage bufferedImage = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        //得到图片
        Graphics2D graphics = (Graphics2D)bufferedImage.getGraphics();
        //设置图片背景颜色
        graphics.setColor(Color.white);
        //填充区域
        graphics.fillRect(0,0,80,30);
        //给图片写数据
        graphics.setColor(Color.blue);
        graphics.setFont(new Font(null,Font.BOLD,20));
        graphics.drawString(makeNum(),0,20);
        //告诉浏览器请求以图片形式打开
        resp.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存
        resp.setHeader("expires", String.valueOf(-1));
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");
        //把图片写给浏览器
        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
    }

    private String makeNum(){
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuffer stringbuffer = new StringBuffer();
        //保证生成的是7位随机数
        for (int i=0;i<7-num.length();i++){
            stringbuffer.append("0");
        }
        num = stringbuffer+num;
        return num;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
