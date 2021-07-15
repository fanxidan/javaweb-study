<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<%--JSP表达式
作用：将程序的结果输出到客户端--%>
<%= new java.util.Date()%>

<%--脚本片段--%>
<%
    int sum = 0;
    for (int i = 0; i <= 100; i++) {
        sum += i;
    }
    out.println("<h1>Sum=" + sum + "</h1>");
%>

<%--在代码中嵌入HTML元素--%>
<%
    for (int i = 0; i < 10; i++) {
%>
    <p>hello</p>
<%
    }
%>

<%--jsp声明--%>
<%!
    static {
        System.out.println("hello");
    }
    private int num = 0;
%>

<%--定制错误页面--%>
<%@ page errorPage="500error.jsp" %>
</body>
</html>
