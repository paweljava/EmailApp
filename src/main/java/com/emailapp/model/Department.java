package com.emailapp.model;

import java.util.List;
import java.util.UUID;

public class Department {
    private UUID uuid;
    private String departmentName;
    private List <Employee> employeeList;

    public Department(UUID uuid, String departmentName) {
        this.uuid = uuid;
        this.departmentName = departmentName;
    }

    public Department(UUID uuid, String departmentName, List<Employee> employeeList) {
        this.uuid = uuid;
        this.departmentName = departmentName;
        this.employeeList = employeeList;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
//MARKETING, OPERATIONS, FINANCE, SALES, HR, PURCHASE, RESEARCH, PRODUCTION, SERVICE
}
