package com.emailapp.service;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;

import java.util.Random;
import java.util.Scanner;
import java.util.UUID;


public class EmailAppConsole {
    static String passwordSource = "qwertyuiopasdfghjklzxcvbnm";
    EmailAppCrud emailAppCrud = new EmailAppCrud();


    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    public Company inputCompanyName() {
        System.out.print("Type company name: ");
        var name = readLine();
        return new Company(UUID.randomUUID(), name);
    }

    public String inputDepartmentName() {
        System.out.println("Type department name:");
        return readLine();

        /*emailAppCrud.getAllCompanyList().stream()
                .map(Company::getCompanyName)
                .filter(r -> r.equals(companyName))
                .forEach(System.out::println);*/

        /*for (Company company : emailAppCrud.getCompanyList()) {
            if (company.getCompanyName().equals(companyName)) {
                final var department = new Department(UUID.randomUUID(), departmentName);
                getDepartmentList().add(department);
                System.out.println("Added department to restaurant" + companyName);
                System.out.println();
                return department;
            }
        }
        throw new IllegalStateException();*/
    }

    public Employee inputEmployeeData() {
        System.out.print("Type employee firstname: ");
        var firstName = readLine();
        System.out.print("Type employee lastName: ");
        var lastName = readLine();
        System.out.println(emailAppCrud.getCompanyList());
        System.out.print("Type employee company name from list: ");
        var companyName = readLine();
        for (Company company : emailAppCrud.getCompanyList()) {
            if (company.getCompanyName().equals(companyName)) {
                companyName = company.getCompanyName();
                System.out.println();
            }
        }
        System.out.print("Type employee department name: ");
        var departmentName = readLine();
        for (Department department : emailAppCrud.getDepartmentList()) {
            if (department.getDepartmentName().equals(departmentName)) {
                departmentName = department.getDepartmentName();
                System.out.println();
            }
        }
        String address = firstName + "." + lastName + "." + departmentName + "@" + companyName + ".com";
        int capacity = 100;
        String password = generatePassword(5);
        Email email = new Email(password, capacity, address);
        var employee = new Employee(UUID.randomUUID(), firstName, lastName, email);
        //var employee = new Employee(firstName, lastName, departmentName, email);
        return employee;
    }

    private String generatePassword(int passwordSize) {
        Random random = new Random();
        var result = new StringBuilder();
        for (int i = 0; i < passwordSize; i++) {
            result.append(passwordSource.toCharArray()[random.nextInt(passwordSource.length())]);
        }
        return result.toString();
    }

/*    private boolean isValidDepartment(String consoleInputType) {
        try {
            Department.valueOf(consoleInputType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
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
    }*/
}