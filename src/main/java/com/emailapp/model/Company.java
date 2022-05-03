package com.emailapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Company {
    private UUID uuid = randomUUID();
    private String companyName;
    private List<Department> departmentList = new ArrayList<>();

    public Company(String companyName) {
        this.uuid = UUID.randomUUID();
        this.companyName = companyName;
    }

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