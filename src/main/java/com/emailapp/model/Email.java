package com.emailapp.model;

public class Email {
    private String address;
    private String password;
    private int capacity;

    public Email(String address, String password, int capacity) {
        this.address = address;
        this.password = password;
        this.capacity = capacity;
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
