package com.emailapp.service;

import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;

import java.util.*;

public class EmailAppConsole {

    public EmailAppConsole(List<Employee> employee){
        this.employeeList = employee;
    }

    public EmailAppConsole() {
        this.employeeList = new ArrayList<>(List.of());
    }
    private final List <Employee> employeeList;

    public Employee inputEmployeeData() {
        Department[] departments = Department.values();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type employee firstname: ");
        String firstName = scanner.nextLine();
        System.out.print("Type employee lastName: ");
        String lastName = scanner.nextLine();
        Department department = typeValidation(scanner, departments);
        var employee = new Employee(firstName, lastName, department);
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
}
