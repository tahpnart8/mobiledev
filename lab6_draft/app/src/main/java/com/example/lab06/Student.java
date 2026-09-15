package com.example.lab06;

import java.io.Serializable;

public class Student implements Serializable {

    private String studentId;
    private String name;
    private String gender;
    private int age;

    public Student(
            String studentId,
            String name,
            String gender,
            int age) {

        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}