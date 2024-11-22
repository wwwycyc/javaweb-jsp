package com.example.student.Model;

public class Sclass {
    String sclass;
    String smajor;

    public Sclass(String sclass, String smajor) {
        this.sclass = sclass;
        this.smajor = smajor;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSmajor() {
        return smajor;
    }

    public void setSmajor(String smajor) {
        this.smajor = smajor;
    }
}
