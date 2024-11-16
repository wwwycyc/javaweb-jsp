<%--
  Created by IntelliJ IDEA.
  User: wwwwyycc
  Date: 2024/10/19
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.student.Model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
        }
        th {
            background-color: #f2f2f2;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
        }
        th {
            background-color: #f2f2f2;
        }
        .content {
            text-align: center;
            margin: auto;
            width: 80%;
        }
        .content table {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<div class="content">
    <h2>学生信息</h2>
    <a href="addStudent">增加</a>|<a href="findStudent">查询</a>
    <br><br>
    <table>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>专业</th>
            <th>班级</th>
            <th>操作</th>
        </tr>

        <%
            List<Student> students = (List<Student>) request.getAttribute("stu_list");
            if (students!=null){

                for (Student stu:students){
        %>
            <tr>
            <td><%=stu.getStuNo()%></td>
            <td><%=stu.getStuName()%></td>
            <td><%=stu.getStuClass()%></td>
            <td><%=stu.getMajor()%></td>
            <td>
                <a href="updateStudent?id=<%=stu.getStuNo()%>">修改</a> |
                <a href="deleteStudent?id=<%=stu.getStuNo()%>" onclick="return confirm('确定删除吗？')">删除</a>
            </td>
            </tr>
        <%
                }
            }
        %>

    </table>
    <%
        //TODO 为什么这样当前的URI是/*/index.jsp
//    String requestURI = request.getRequestURI();
//    System.out.println(requestURI);
//    if (requestURI.contains("/findStudent")) {
        String URL = (String) request.getAttribute("referer");
        if(URL != null && URL.contains("findStudent")){
    %>
    <a href="index">返回首页</a>
    <%
        }
    %>
</div>

</body>
</html>