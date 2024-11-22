<%--
  Created by IntelliJ IDEA.
  User: wwwwyycc
  Date: 2024/11/1
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sun.jdi.connect.spi.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询学生</title>
    <style>
        body {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>查询</h1>
<form action="findStudent" method="post">
    学号：<input type="text" name="id"><br>
    姓名：<input type="text" name="name"><br>
    专业：
    <select name="major">
        <option value="不限专业">不限专业</option>
        <c:forEach var="major1" items="${requestScope.major_List}">
            <option value="${major1}">${major1}</option>
        </c:forEach>
    </select><br>

    班级：
    <select name="stuClass">
        <option value="不限班级">不限班级</option>
        <c:forEach var="class1" items="${requestScope.class_List}">
            <option value="${class1}">${class1}</option>
        </c:forEach>
    </select><br>

    <input type="submit" value="查询">
</form>
</body>
</html>
