package com.emailapp.model;

public class Email {
    private String password;
    private int capacity;
    private String address;

    public Email(String password, int capacity, String address) {
        this.password = password;
        this.capacity = capacity;
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
