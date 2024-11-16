<%@page import="java.util.List"%>
<%@ page import="com.example.student.Model.Student"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加新学生</title>
    <style>
        form, h2 {
            text-align: center;
        }
        a {
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>
<h2>增加新学生</h2>
<form action="addStudent" method="post">
    学号：<input type="text" name="id"><br>
    姓名：<input type="text" name="name"><br>
    班级：<select name="stuClass">
    <%
        List<String> sclass = (List<String>) request.getAttribute("class_List");
        if (sclass != null) {
            for (String sclass1 : sclass) {
    %>
    <option value="<%= sclass1 %>"><%= sclass1 %></option>
    <%
            }
        }
    %>
</select><br>
    <input type="submit" value="添加">
    <button><a href="index">返回学生列表</a></button>
</form>
</body>
</html>