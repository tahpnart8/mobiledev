package com.example.lab6;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String teacher;
    private ArrayList<Student> students;

    public ClassInfo() {
    }

    public ClassInfo(String name, String teacher, ArrayList<Student> students) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
