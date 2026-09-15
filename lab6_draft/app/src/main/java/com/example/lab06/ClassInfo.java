package com.example.lab06;

import java.util.ArrayList;

public class ClassInfo {

    private String className;
    private String homeroomTeacher;
    private ArrayList<Student> students;

    public ClassInfo(
            String className,
            String homeroomTeacher,
            ArrayList<Student> students) {

        this.className = className;
        this.homeroomTeacher = homeroomTeacher;
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public String getHomeroomTeacher() {
        return homeroomTeacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getStudentCount() {
        return students.size();
    }
}