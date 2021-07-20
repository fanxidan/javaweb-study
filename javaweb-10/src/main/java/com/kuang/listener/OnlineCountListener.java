package com.kuang.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网站在线人数：本质是统计session
public class OnlineCountListener implements HttpSessionListener {
    //创建session的监听
    //一旦创建一个session就会触发一次事件
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        if (onlineCount == null){
            onlineCount = 1;
        }else {
            int count = onlineCount.intValue();
            onlineCount = count + 1;
        }
        servletContext.setAttribute("OnlineCount",onlineCount);
    }
    //销毁session的监听
    //一旦销毁一个session就会触发一次事件
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        if (onlineCount == null){
            onlineCount = 0;
        }else {
            int count = onlineCount.intValue();
            onlineCount = count - 1;
        }
        servletContext.setAttribute("OnlineCount",onlineCount);
    }
//    session销毁：
//    1、手动销毁
//    2、自动销毁
}
