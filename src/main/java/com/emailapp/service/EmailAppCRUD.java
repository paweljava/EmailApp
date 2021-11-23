package com.emailapp.service;

import com.emailapp.model.Department;
import com.emailapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmailAppCRUD {

    /*public EmailAppCRUD (List <Employee> employee) {
        this.employeeList = employee;
    }*/

    public EmailAppCRUD() {
        this.employeeList = new ArrayList<>();
    }

    private final List<Employee> employeeList;

    // Create
    public Employee employeeCreate(String firstName, String lastName, Department department) {

        Employee employee = new Employee(firstName, lastName, department);
        employeeList.add(employee);
        return employee;
    }
    // Read
    public List<Employee> getEmployeeList () {
        return employeeList;
    }
    // Update
    // Delete
}
