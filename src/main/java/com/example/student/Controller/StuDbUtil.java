package com.example.student.Controller;

import com.example.student.Model.Result;
import com.example.student.Model.Student;
import com.mysql.cj.jdbc.Driver;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuDbUtil {
    private String JDBCURL = "jdbc:mysql://localhost:3306/mybatis";
    private String JDBCUSERNAME = "root";
    private String JDBCPASSWORD = "root";

    public StuDbUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBCURL, JDBCUSERNAME, JDBCPASSWORD);
    }

    public Result<List<Student>> getAllStudent() {
        String sql = "SELECT * FROM mybatis.student left join mybatis.class_major on student.sclass=class_major.sclass";
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                String stuNo = resultSet.getString("id");
                String stuName = resultSet.getString("name");
                String stuClass = resultSet.getString("sclass");
                String major = resultSet.getString("major");
                Student stu = new Student(stuNo, stuName, stuClass, major);
                students.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result<>(500, "error", null);
        }
        return new Result<>(200, "success", students);
    }

    public Result<String> addStudent(Student student) {
        String stuNo = student.getStuNo();
        String stuName = student.getStuName();
        String stuClass = student.getStuClass();
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO mybatis.student (id, name, sclass) VALUES (?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, stuNo);
            statement.setString(2, stuName);
            statement.setString(3, stuClass);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return new Result<>(200, stuNo + "添加成功", stuNo);
            } else {
                return new Result<>(500, "Error", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 1062)
                return new Result<>(500, "该学生已存在", null);
            else {
                return new Result<>(500, "Error", null);
            }

        }
    }

    public Result<String> deleteStudent(String stuNo) {
        String sql = "DELETE FROM mybatis.student WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, stuNo);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return new Result<>(200, "删除成功", null);  // 删除成功
            } else {
                return new Result<>(500, "删除失败，学生不存在", null);  // 删除失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result<>(500, "数据库错误", null);  // 数据库错误
        }
    }

    public Result<String> updateStudent(String stuNo, String name, String sclass) {
        String sql = "UPDATE mybatis.student SET name = ?, sclass = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, sclass);
            statement.setString(3, stuNo);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return new Result<>(200, "更新成功", null);
            } else {
                return new Result<>(500, "Error", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result<>(500, "Error", null);
        }
    }

    public Result<Student> getStudentById(String stuNo) {
        String sql = "SELECT * FROM mybatis.student left join mybatis.class_major on student.sclass=class_major.sclass where id=?";
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, stuNo);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String stuName = resultSet.getString("name");
                String stuClass = resultSet.getString("sclass");
                String major = resultSet.getString("major");
                student = new Student(stuNo, stuName, stuClass, major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result<>(500, "error", null);
        }
        return new Result<>(200, "success", student);
    }

    public Result<List<Student>> ElasticSearch(String stuNo,String stuName,String stuClass,String major){
        List<Student> students = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM mybatis.student left join mybatis.class_major cm on student.sclass = cm.sclass WHERE 1=1 ");
        if (stuNo != null && !stuNo.trim().isEmpty()) {
            sql.append(" AND student.id LIKE '%").append(stuNo).append("%'");
        }
        if (stuName != null && !stuName.trim().isEmpty()) {
            sql.append(" AND student.name LIKE '%").append(stuName).append("%'");
        }
        if (major != null && !major.equals("不限专业")&& !major.trim().isEmpty()) {
            sql.append(" AND major LIKE '%").append(major).append("%'");
        }
        if (stuClass != null && !stuClass.equals("不限班级") && !stuClass.trim().isEmpty()) {
            sql.append(" AND student.sclass LIKE '%").append(stuClass).append("%'");
        }
        try(Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql.toString())
        ){
            while (resultSet.next()) {
                String sNo = resultSet.getString("id");
                String sName = resultSet.getString("name");
                String sClass = resultSet.getString("sclass");
                String smajor = resultSet.getString("major");
                Student stu = new Student(sNo, sName, sClass, smajor);
                students.add(stu);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return new Result<>(500, "error", null);
        }
        return new Result<>(200, "success", students);
    }
}
