package com.emailapp.service;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmailAppCrud {


    private Company company1 = new Company(UUID.randomUUID(), "a");
    private Company company2 = new Company(UUID.randomUUID(), "b");
    private Company company3 = new Company(UUID.randomUUID(), "c");
    private Company company4 = new Company(UUID.randomUUID(), "d");
    private List<Company> companyList = new ArrayList<>(List.of(company1, company2, company3, company4));
    //private List<Department> departmentList = new ArrayList<>();
    //private List<Employee> employeeList = new ArrayList<>();

    /*public EmailAppCrud() {
        this.companyList = new ArrayList<>();
    }

    public EmailAppCrud(List<Company> companyList) {
        this.companyList = companyList;
    }*/

    // Create
    public Company companyCreate(UUID uuid, String name) {
        Company company = new Company(uuid, name);
        companyList.add(company);
        return company;
    }
   /* public Department departmentCreate(UUID uuid, String companyName, String departmentName) {
        Department department = new Department(uuid, companyName, departmentList);
        //getEmployeeList();

        getDepartmentList().add(department);

        return department;
    }*/

    /*    public Company companyCreate(UUID uuid, String name, List<Department> departmentList) {
            Company company = new Company(UUID uuid, String name, departmentList);
            companyList.add (company);
            return company;
        }*/
//    public boolean employeeCreate(Employee employee) {
//        //Employee employee = new Employee(uuid, firstName, lastName, email);
//        companyList(getDepartmentList(getEmployeeList().add(employee)));
//        return ;
//    }
    // Read
    /*public List<Employee> getEmployeeList() {
        return employeeList;
    }*/

    public List<Company> getCompanyList() {
        return companyList;
    }

    /*public List<Department> getDepartmentList() {
        return departmentList;
    }*/


    // Update

    // Delete
    public void employeeDelete(String name) {

    }
}
