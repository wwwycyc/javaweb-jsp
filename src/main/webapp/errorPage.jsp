<%--
  Created by IntelliJ IDEA.
  User: wwwwyycc
  Date: 2024/11/16
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            text-align: center;
            margin-top: 50px;
        }
        .error-container {
            background-color: #fff;
            border: 1px solid #e0e0e0;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
        .error-container h1 {
            color: #d9534f;
        }
        .error-container p {
            color: #333;
        }
        .error-container a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #5bc0de;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .error-container a:hover {
            background-color: #31b0d5;
        }
    </style>
</head>
<body>
<div class="error-container">
<%
    String MSG = request.getAttribute("Msg").toString();
    String URL = request.getAttribute("referer").toString();
%>
    <h1><%=MSG%></h1>

    <a href=<%=URL%>>返回上一步</a>
    <a href="index">返回学生列表</a>

</div>
</body>
</html>
