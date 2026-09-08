package com.example.lab4;

public class Employee {

    private final String name;
    private final String dob;
    private final boolean gender; // true = Nam, false = Nu
    private final String hobby;
    private final String elevel;

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

    public String getDob() {
        return dob;
    }

    public boolean isGender() {
        return gender;
    }

    public String getHobby() {
        return hobby;
    }

    public String getElevel() {
        return elevel;
    }
}
