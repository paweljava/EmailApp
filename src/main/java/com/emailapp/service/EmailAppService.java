package com.emailapp.service;

import com.emailapp.model.Employee;

import java.util.Scanner;

public class EmailAppService {

    public EmailAppCrud emailAppCRUD = new EmailAppCrud();
    public EmailAppConsole emailAppConsole = new EmailAppConsole();



    //private final Employee employee = new ArrayList<>();

    public void process() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type \"exit\" to exit ");
            System.out.println("Type \"1\" to create employee ");
            System.out.println("Type \"2\" to ");
            System.out.println("Type \"3\" to ");
            System.out.println("Type \"4\" to ");
            System.out.print("What is yours choose: ");
            String choose = scanner.nextLine();

            switch (choose) {
                case "exit" -> exit();
                case "1" -> getEmployee();
               // case "2" -> ;
               // case "3" -> ;
               // case "4" -> ;*/
                default -> System.out.println("Type correct value!");
            }
        }
    }

    public Employee getEmployee() {
        Employee employee = emailAppConsole.inputEmployeeData();
        return emailAppCRUD.employeeCreate(employee.getFirstName(), employee.getLastName(), employee.getDepartment());
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }


}
