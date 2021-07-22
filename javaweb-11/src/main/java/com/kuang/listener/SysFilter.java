package com.kuang.listener;

import com.kuang.util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getSession().getAttribute(Constant.USER_SESSION) == null){
            System.out.println("doFilter USER_SESSION is null");
            response.sendRedirect("/javaweb_11/error.jsp");
        }
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
