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

@WebServlet(name = "deleteStudentServlet",value = "/deleteStudent")
public class deleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuNo = request.getParameter("id");  // 获取学生学号

        if (stuNo != null) {
            try {
                // 调用数据库操作删除学生
                StuDbUtil dbUtil = new StuDbUtil();
                Result<String> result = dbUtil.deleteStudent(stuNo);

                if (result.getCode() == 200) {
                    // 删除成功后，重定向到学生列表页面
                    response.sendRedirect("index");  // 这里会触发 getAllStudentServlet
                } else {
                    // 删除失败，转发到错误页面
                    request.setAttribute("Msg", result.getMsg());
                    request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("Msg", "删除失败，发生了错误");
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        } else {
            // 如果没有传递学生ID，跳转到错误页面
            request.setAttribute("Msg", "未找到学生信息");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}
