<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<%--前面没有/,提交表单后会跳转到loginservlet--%>
<form action="servlet/login" method="post">
    <input type="text" name="username">
    <input type="submit">
</form>
</body>
</html>
