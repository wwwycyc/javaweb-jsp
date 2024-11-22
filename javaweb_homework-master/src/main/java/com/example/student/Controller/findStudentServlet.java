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

@WebServlet(name = "findStudentServlet",value = "/findStudent")
public class findStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Result<List<String>> allClass = new Class_MajorDbUtil().getAllClass();
        Result<List<String>> allMajor = new Class_MajorDbUtil().getAllMajor();
        request.setAttribute("class_List",allClass.getData());
        request.setAttribute("major_List",allMajor.getData());
        RequestDispatcher dispatcher = request.getRequestDispatcher("findStudent.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String stuNo = request.getParameter("id");
        String stuName = request.getParameter("name");
        String stuClass = request.getParameter("stuClass");
        String major = request.getParameter("major");
        String referer = request.getHeader("Referer");
        Result<List<Student>> result = new StuDbUtil().ElasticSearch(stuNo,stuName,stuClass,major);
        if (result.getCode() == 200) {
            request.setAttribute("stu_list",result.getData());
            request.setAttribute("referer", referer);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
            //TODO 同上个问题
            //response.sendRedirect("index");
        } else {
            request.setAttribute("Msg",result.getMsg());
            request.setAttribute("referer", referer);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

}
