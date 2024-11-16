package com.example.student.Controller;

import com.example.student.Model.Result;
import com.example.student.Model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addStudentServlet",value = "/addStudent")
public class addStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Result<List<String>> allClass = new Class_MajorDbUtil().getAllClass();
        request.setAttribute("class_List",allClass.getData());
        RequestDispatcher dispatcher = request.getRequestDispatcher("addStudent.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String stuNo = request.getParameter("id");
        String stuName = request.getParameter("name");
        String stuClass = request.getParameter("stuClass");
        String referer = request.getHeader("Referer");
        Student student = new Student(stuNo, stuName, stuClass);
        Result<String> result = new StuDbUtil().addStudent(student);
        if (result.getCode() == 200) {
            response.sendRedirect("index");
        } else {
            request.setAttribute("Msg",result.getMsg());
            request.setAttribute("referer", referer);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}
