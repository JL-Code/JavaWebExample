<%--
  Created by IntelliJ IDEA.
  User: codeme
  Date: 2020/1/3
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/DynamicWebProject03_IDEA/fileupload" enctype="multipart/form-data" method="post">
        普通表单字段:
        <input type="text" name="username">
        文件字段:
        <input type="file" name="file">
        <button type="submit">提交表单</button>
    </form>
    我是JSP文件
</body>
</html>
