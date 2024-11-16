package com.example.student.Model;

public class Student {
    public String stuName;
    public String stuNo;
    public String stuClass;
    public String major;
    public Student(String stuNo,String stuName,String stuClass){
        this.stuName = stuName;
        this.stuNo = stuNo;
        this.stuClass = stuClass;
    }
    public Student(String stuNo,String stuName,String stuClass,String major)
    {
        this.stuName = stuName;
        this.stuNo = stuNo;
        this.stuClass = stuClass;
        this.major = major;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
