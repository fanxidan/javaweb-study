package com.kuang.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    //初始化:web服务器启动，就初始化了，随时等待过滤器对象的出现
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter启动了");
    }
    /*
    * 过滤器中的所有代码，在过滤特定的请求的时候都会执行
    * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        System.out.println("CharacterEncodingFilter执行前");
        //让请求继续走，如果不写，传讯到这里被拦截停止
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("CharacterEncodingFilter执行后");
    }
    //销毁:web服务器关闭的时候销毁
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");
    }
}
