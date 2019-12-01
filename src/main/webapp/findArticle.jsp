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
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                var aa = $("#aa").val();
                var page = 0;
                $.ajax({
                    url: "${pageContext.request.contextPath}/article/findArticle",
                    data: {"aa": aa, "page": page},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        $("ul,hr").remove();
                        $.each(data, function (index, article) {
                            var nameli = $("<li>").html("题目：" + article.name);
                            var contentli = $("<li>").html("内容：" + article.content);
                            var hrefli = $("<a>").html("点击了解更多").attr("href", article.href);
                            var hr = $("<hr>");
                            var ul = $("<ul>").append(nameli).append(contentli).append(hrefli);
                            $("#bd").append(hr).append(ul);
                        })
                    }
                })

            })
            $("#page1").click(function () {
                var aa = $("#aa").val();
                var page1 = $("#currentPage").val() - 1;
                $.ajax({
                    url: "${pageContext.request.contextPath}/article/findArticle",
                    data: {"aa": aa, "page": page1},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        $("ul,hr").remove();
                        $.each(data, function (index, article) {
                            var nameli = $("<li>").html("题目：" + article.name);
                            var contentli = $("<li>").html("内容：" + article.content);
                            var hrefli = $("<a>").html("点击了解更多").attr("href", article.href);
                            var hr = $("<hr>");
                            var ul = $("<ul>").append(nameli).append(contentli).append(hrefli);
                            $("#bd").append(hr).append(ul);
                        })
                    }
                })
                $("#currentPage").val(page1)
            })
            $("#page2").click(function () {
                var aa = $("#aa").val();
                var page2 = parseInt($("#currentPage").val()) + 1;
                $.ajax({
                    url: "${pageContext.request.contextPath}/article/findArticle",
                    data: {"aa": aa, "page": page2},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        $("ul,hr").remove();
                        $.each(data, function (index, article) {
                            var nameli = $("<li>").html("题目：" + article.name);
                            var contentli = $("<li>").html("内容：" + article.content);
                            var hrefli = $("<a>").html("点击了解更多").attr("href", article.href);
                            var hr = $("<hr>");
                            var ul = $("<ul>").append(nameli).append(contentli).append(hrefli);
                            $("#bd").append(hr).append(ul);
                        })
                    }
                })
                $("#currentPage").val(page2)
            })
            $("#page3").click(function () {
                var aa = $("#aa").val();
                var page3 = $("#currentPage").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/article/findArticle",
                    data: {"aa": aa, "page": page3},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        $("ul,hr").remove();
                        $.each(data, function (index, article) {
                            var nameli = $("<li>").html("题目：" + article.name);
                            var contentli = $("<li>").html("内容：" + article.content);
                            var hrefli = $("<a>").html("点击了解更多").attr("href", article.href);
                            var hr = $("<hr>");
                            var ul = $("<ul>").append(nameli).append(contentli).append(hrefli);
                            $("#bd").append(hr).append(ul);
                        })
                    }
                })
                $("#currentPage").val(page3)
            })
        })


    </script>
</head>
<body id="bd">
<center>
    <input type="text" id="aa">
    <input type="button" id="btn" value="点击搜索"></button><br/>
    <input type="button" id="page1" value="上一页"></button>
    <input type="text" id="currentPage" value="1">
    <input type="button" id="page3" value="跳转到此页"></button>
    <input type="button" id="page2" value="下一页"></button>
    <ul id="u_ul"></ul>
</center>
</body>
</html>
