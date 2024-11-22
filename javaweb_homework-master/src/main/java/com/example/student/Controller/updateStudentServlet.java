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

@WebServlet(name = "updateStudentServlet",value = "/updateStudent")
public class updateStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获得学生信息
        String id = request.getParameter("id");
        Result<Student> result = new StuDbUtil().getStudentById(id);
        request.setAttribute("student",result.getData());
        //获得全部班级
        Result<List<String>> allClass = new Class_MajorDbUtil().getAllClass();
        request.setAttribute("class_List",allClass.getData());
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateStudent.jsp");
        dispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sclass = request.getParameter("sclass");
        String referer = request.getHeader("Referer");

        if (id == null || name == null || sclass == null) {
            request.setAttribute("Msg","修改学生的信息不能有空值");
            request.setAttribute("referer", referer);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }

        StuDbUtil dbUtil = new StuDbUtil();
        try {
            Result<String> result = dbUtil.updateStudent(id, name, sclass);

            if (result.getCode() == 200) {
                response.sendRedirect("index");
            } else {
                // 更新失败，跳转到错误页面
                request.setAttribute("Msg", result.getMsg());
                request.setAttribute("referer", referer);
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Msg", "Error");
            request.setAttribute("referer", referer);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}
