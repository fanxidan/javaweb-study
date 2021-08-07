package com.kuang.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.kuang.pojo.User;
import com.kuang.service.user.UserService;
import com.kuang.service.user.UserServiceImpl;
import com.kuang.util.Constants;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

//实现servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        //pwdmodify.jsp中的method
        if (method.equals("savepwd") && method != null) {
            this.updatePwd(req,resp);
        } else if(method.equals("pwdmodify") && method != null) {   //pwdmodify.js中的method
            this.pwdModify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session中拿取id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newPassword = req.getParameter("newpassword");
        boolean flag = false;
        if (attribute != null && !StringUtils.isNullOrEmpty(newPassword)){
            UserService userService = new UserServiceImpl();
            try {
                flag = userService.updatePwd(((User) attribute).getId(), newPassword);
                if (flag) {
                    req.setAttribute("message","修改密码成功，请退出，使用新密码登录");
                    //密码修改成功，移除当前session
                    req.getSession().removeAttribute(Constants.USER_SESSION);
                }else {
                    req.setAttribute("message","修改密码失败");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else {
            req.setAttribute("message","新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    //验证旧密码:session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从session里面拿ID
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        HashMap<String, String> resultMap = new HashMap<>();
        if (o ==null){  //session失效,session过期了
            //对应pwdmodify.js中oldpassword.on
            resultMap.put("result","sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)){ //输入的密码为空，
            resultMap.put("result","error");
        }else {
            String userPassword = ((User) o).getUserPassword();//session中用户的密码
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //JSONARRAY 阿里巴巴JSON工具类，转换格式
            writer.write(JSONArray.toJSONString(resultMap));//传递给前端
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
