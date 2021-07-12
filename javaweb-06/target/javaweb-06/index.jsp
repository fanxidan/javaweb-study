<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>登录</title>
<body>
<h2>登录</h2>
<div style="text-align: center">
<%--    以post的方式提交表单到login请求--%>
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        爱好：
        <input type="checkbox" name="hobby" value="女孩">女孩
        <input type="checkbox" name="hobby" value="代码">代码
        <input type="checkbox" name="hobby" value="唱歌">唱歌
        <input type="checkbox" name="hobby" value="电影">电影
        <input type="submit">
    </form>
</div>
</body>
</html>
