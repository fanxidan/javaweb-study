<%--
  Created by IntelliJ IDEA.
  User: 王金龙
  Date: 2021/7/19
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="people" class="com.kuang.pojo.People" scope="page" />
<jsp:setProperty name="people" property="address" value="xian"/>
<jsp:setProperty name="people" property="id" value="001"/>
<jsp:setProperty name="people" property="name" value="fxd"/>

姓名<jsp:getProperty name="people" property="name"/>
id<jsp:getProperty name="people" property="id"/>
地址<jsp:getProperty name="people" property="address"/>
</body>
</html>
