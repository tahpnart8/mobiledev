package com.example.lab4;

import java.io.Serializable;

// Serializable de object dong goi duoc vao Intent gui sang man hinh danh sach
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String dob;
    private boolean gender; // true = Nam, false = Nu
    private String hobby;
    private String elevel;

    public Employee() {
    }

    public Employee(String name, String dob, boolean gender, String hobby, String elevel) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.hobby = hobby;
        this.elevel = elevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getElevel() {
        return elevel;
    }

    public void setElevel(String elevel) {
        this.elevel = elevel;
    }
}
