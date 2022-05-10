package com.emailapp.service;

import com.emailapp.repository.CompanyRepository;

import java.util.Random;
import java.util.Scanner;


public class InputProcessor {
    static String passwordSource = "qwertyuiopasdfghjklzxcvbnm";
    CompanyRepository companyRepository = new CompanyRepository();


    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    public String inputCompanyName() {
        var name = readLine();
        System.out.println();
        return name;
    }

    public String inputDepartmentName() {
        var name = readLine();
        System.out.println();
        return name;
    }

    /*public Employee inputEmployeeData() {
    }*/


}