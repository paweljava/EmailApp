package com.emailapp.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final Department department;
    private final Email email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee(String firstName, String lastName, Department department, Email email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;

    }
}
