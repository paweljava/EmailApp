package com.emailapp.service;

import java.util.Scanner;


public class InputProcessor {
    static String passwordSource = "qwertyuiopasdfghjklzxcvbnm";
    //CompanyRepository companyRepository = new CompanyRepository();

    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    public String inputCompanyName() {
        var name = readLine();
        System.out.println();
        return name;
    }

    public String inputNewCompanyName() {
        var name = readLine();
        System.out.println();
        return name;
    }

    public String inputDepartmentName() {
        var name = readLine();
        System.out.println();
        return name;
    }

    public String inputNewDepartmentName() {
        var name = readLine();
        System.out.println();
        return name;
    }

    public String inputEmployeeData() {
        var name = readLine();
        System.out.println();
        return name;
    }


}