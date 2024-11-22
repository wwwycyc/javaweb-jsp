<%--
  Created by IntelliJ IDEA.
  User: wwwwyycc
  Date: 2024/10/19
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>编辑学生信息</title>
  <style>
    body{
      text-align: center;
    }
  </style>
</head>
<body>
<h2>编辑学生信息</h2>

<form action="updateStudent" method="post">
  学号：<input type="text" name="id" value="${requestScope.student.stuNo}" readonly style="background: #e0e0e0"><br>
  姓名：<input type="text" name="name" value="${requestScope.student.stuName}" ><br>
  班级：<select name="sclass">
  <c:forEach var="class1" items="${requestScope.class_List}">
    <option value="${class1}">${class1}</option>
  </c:forEach>
</select><br>
  <input type="submit" value="更新">
</form>

<a href="index">返回学生列表</a>

</body>
</html>
