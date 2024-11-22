package com.example.student.Controller;

import com.example.student.Model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Class_MajorDbUtil {
    private String JDBCURL="jdbc:mysql://localhost:3306/mybatis";
    private String JDBCUSERNAME="root";
    private String JDBCPASSWORD="root";
    public Class_MajorDbUtil(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBCURL,JDBCUSERNAME,JDBCPASSWORD);
    }

    public Result<List<String>> getAllClass(){

        String sql = "SELECT sclass FROM mybatis.class_major";
        List<String> sclasss = new ArrayList<>();
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ){
            while (resultSet.next()){
                String sclass = resultSet.getString("sclass");
                sclasss.add(sclass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if(sclasss==null){
            return new Result<>(500,"系统暂未设定班级",null);
        }
        return new Result<>(200,"success",sclasss);
    }
    public Result<List<String>> getAllMajor(){

        String sql = "SELECT distinct major FROM mybatis.class_major";
        List<String> major = new ArrayList<>();
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ){
            while (resultSet.next()){
                String sclass = resultSet.getString("major");
                major.add(sclass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if(major==null){
            return new Result<>(500,"系统暂未设定专业",null);
        }
        return new Result<>(200,"success",major);
    }
}
