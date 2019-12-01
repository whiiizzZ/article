<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<center>
    <form method="post" action="${pageContext.request.contextPath}/article/insertArticle">
        标题：<input type="text" name="name">
        作者：<input type="text" name="author">
        类型：<input type="text" name="type">
        内容：<input type="text" name="content">
        <input type="submit" value="确认添加">
    </form>
</center>
</body>
</html>