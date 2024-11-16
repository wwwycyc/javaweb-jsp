<%@ page import="com.sun.jdi.connect.spi.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wwwwyycc
  Date: 2024/11/1
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询学生</title>
    <style>
        body{
            text-align: center;
        }
    </style>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    List<String> sclass = (List<String>) request.getAttribute("class_List");
    List<String> major = (List<String>) request.getAttribute("major_List");
%>
    <h1>查询</h1>
    <form action="findStudent" method="post">
        学号：<input type="text" name="id" ><br>
        姓名：<input type="text" name="name"><br>
        专业：<select name="major">
            <option value="不限专业">不限专业</option>
            <%
                if (major == null)
                    System.out.println("null");
                if (major != null) {
                for (String major1 : major) {
            %>
            <option value="<%= major1%>"><%= major1%></option>
        <%
                }
            }
        %>
    </select>
        班级：<select name="stuClass">
        <option value="不限班级">不限班级</option>
        <%
            if (sclass != null) {
                for (String sclass1 : sclass) {
        %>
        <option value="<%= sclass1%>"><%= sclass1%></option>
        <%
                }
            }
        %>
    </select>
        <input type="submit" value="查询">
    </form>
</body>
</html>
