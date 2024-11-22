<%@ page import="java.util.List" %>
<%@ page import="com.example.student.Model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <c:forEach var="stu" items="${stu_list}">
            <tr>
                <td>${stu.stuNo}</td>
                <td>${stu.stuName}</td>
                <td>${stu.stuClass}</td>
                <td>${stu.major}</td>
                <td>
                    <a href="updateStudent?id=${stu.stuNo}">修改</a> |
                    <a href="deleteStudent?id=${stu.stuNo}" onclick="return confirm('确定删除吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <c:if test="${not empty referer && referer.contains('findStudent')}">
        <a href="index">返回首页</a>
    </c:if>
</div>
</body>
</html>
