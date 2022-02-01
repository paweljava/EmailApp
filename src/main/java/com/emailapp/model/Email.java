package com.emailapp.model;

public class Email {
    private String password;
    private int capacity;

    public Email(String password, int capacity, String address) {
        this.password = password;
        this.capacity = capacity;
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public Email() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
