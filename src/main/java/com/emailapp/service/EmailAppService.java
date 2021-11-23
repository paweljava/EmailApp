package com.emailapp.service;

import java.util.Scanner;

public class EmailAppService {

    public EmailAppCRUD emailAppCRUD = new EmailAppCRUD();
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
               // case "1" -> emailAppCRUD.employeeCreate(emailAppConsole.inputEmployeeData());
               // case "2" -> ;
               // case "3" -> ;
               // case "4" -> ;*/
                default -> System.out.println("Type correct value!");
            }
        }
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }
}
