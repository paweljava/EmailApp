package com.emailapp.model;

public class Email {
    private Employee employee;
    private String password;
    private int capacity;

    public Employee getEmployee() {
        return employee;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
