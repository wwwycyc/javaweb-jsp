<%--
  Created by IntelliJ IDEA.
  User: wwwwyycc
  Date: 2024/10/19
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.sql.*" %>
<%@ page import="com.example.student.Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>编辑学生信息</title>
</head>
<body>
<h2>编辑学生信息</h2>
<%
  Student student = (Student) request.getAttribute("student");
  List<String> sclass = (List<String>) request.getAttribute("class_List");
%>
  <form action="updateStudent" method="post">
    学号：<input type="text" name="id" value="<%= student.getStuNo() %>" readonly><br>
    姓名：<input type="text" name="name" value="<%= student.getStuName() %>" required><br>
    班级：<select name="sclass">
    <%
      if (sclass != null) {
        for (String sclass1 : sclass) {
    %>
    <option value="<%= sclass1%>"><%= sclass1%></option>
    <%
        }
      }
    %>
  </select><br>
    <input type="submit" value="更新">
  </form>
<a href="index">返回学生列表</a>
</body>
</html>