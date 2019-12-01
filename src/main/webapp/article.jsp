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
    <h3>题目：${requestScope.article.name}</h3><br/>
    <h4>作者：${requestScope.article.author}</h4><h4>类型：${requestScope.article.type}</h4><br/>
    <h5>内容：${requestScope.article.content}</h5>
</center>
</body>
</html>