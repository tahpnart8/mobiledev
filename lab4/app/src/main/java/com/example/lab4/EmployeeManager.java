package com.example.lab4;

import java.util.ArrayList;
import java.util.List;

// Giu danh sach nhan vien trong RAM, dung chung cho 2 man hinh
public class EmployeeManager {

    private static final List<Employee> employees = new ArrayList<>();

    public static void add(Employee employee) {
        employees.add(employee);
    }

    public static List<Employee> getAll() {
        return employees;
    }
}
