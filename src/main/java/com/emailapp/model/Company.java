package com.emailapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Company {
    private UUID uuid = randomUUID();
    private String companyName;
    private Department department1 = new Department(uuid, "Sales");
    private Department department2 = new Department(uuid, "HR");
    private Department department3 = new Department(uuid, "Marketing");
    private Department department4 = new Department(uuid, "Finance");
    private Department department5 = new Department(uuid, "Operations");
    private Department department6 = new Department(uuid, "Purchase");
    private Department department7 = new Department(uuid, "Research");
    private Department department8 = new Department(uuid, "Production");
    private Department department9 = new Department(uuid, "Service");
    private List<Department> departmentList = new ArrayList<>(List.of(department1, department2, department3, department4, department5, department6, department7, department8, department9));

    public Company(UUID uuid, String companyName) {
        this.uuid = uuid;
        this.companyName = companyName;
    }

    public Company(UUID uuid, String companyName, List<Department> departmentList) {
        this.uuid = uuid;
        this.companyName = companyName;
        this.departmentList = departmentList;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}