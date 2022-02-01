package com.emailapp.service;

import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;

import java.util.*;

public class EmailAppConsole {

    static String passwordSource = "qwertyuiopasdfghjklzxcvbnm";

    public Employee inputEmployeeData() {
        Department[] departments = Department.values();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type employee firstname: ");
        String firstName = scanner.nextLine();
        System.out.print("Type employee lastName: ");
        String lastName = scanner.nextLine();
        Department department = typeValidation(scanner, departments);
        String address = firstName + "." + lastName + "." + department + "@company.com";
        int capacity = 100;
        String password = generatePassword(5);
        Email email = new Email(password, capacity, address);
        var employee = new Employee(firstName, lastName, department, email);
        return employee;
    }

    private Department typeValidation(Scanner scanner, Department[] departments) {
        while (true) {
            System.out.print("Type department name. Available types: ");
            for (Department k : departments) {
                System.out.print(k.name() + " ");
            }
            var consoleInputType = scanner.nextLine().toUpperCase(Locale.ROOT);
            if (isValidDepartment(consoleInputType)) {
                return Department.valueOf(consoleInputType);
            }
        }
    }

    private boolean isValidDepartment(String consoleInputType) {
        try {
            Department.valueOf(consoleInputType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private String generatePassword(int passwordSize) {
        Random random = new Random();
        var result = new StringBuilder();
        for (int i = 0; i < passwordSize; i++) {
            result.append(passwordSource.toCharArray()[random.nextInt(passwordSource.length())]);
        }
        return result.toString();
    }
}