package com.emailapp.service;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;

import java.util.Scanner;
import java.util.UUID;

public class EmailAppService {

    /*    public EmailAppCrud emailAppCRUD = new EmailAppCrud();
        public EmailAppConsole emailAppConsole = new EmailAppConsole() {
        };*/
    private final EmailAppCrud emailAppCrud;
    private final EmailAppConsole emailAppConsole;

    public EmailAppService(EmailAppCrud emailAppCrud, EmailAppConsole emailAppConsole) {
        this.emailAppCrud = emailAppCrud;
        this.emailAppConsole = emailAppConsole;
    }


    //private final Employee employee = new ArrayList<>();

    public void process() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type \"exit\" to exit ");
            System.out.println("Type \"1\" to create company ");
            System.out.println("Type \"2\" to create departmet ");
            System.out.println("Type \"3\" to create employee ");
            System.out.println("Type \"4\" to show companies ");
            System.out.println("Type \"5\" to show departmets ");
            System.out.println("Type \"6\" to show employees ");
            System.out.println("Type \"7\" to edit company name");
            System.out.println("Type \"8\" to edit department name");
            System.out.println("Type \"9\" to edit employee");
            System.out.println("Type \"10\" to delete company");
            System.out.println("Type \"11\" to delete department ");
            System.out.println("Type \"12\" to delete employee ");
            System.out.print("What is yours choose: ");
            String choose = scanner.nextLine();

            switch (choose) {
                case "exit" -> exit();
                case "1" -> createCompany();
                case "2" -> createDepartment();
                case "3" -> createEmployee();
                case "4" -> showCompanies();
                //case "4" -> emailAppCrud.getCompanyList();
                case "5" -> emailAppCrud.getDepartmentList();
                case "6" -> emailAppCrud.getEmployeeList();
                // case "3" -> ;
                // case "4" -> ;
                // case "5" -> ;
                // case "6" -> ;*/
                default -> System.out.println("Type correct value!");
            }
        }
    }


    public Company createCompany() {
        Company company = emailAppConsole.inputCompanyName();
        return emailAppCrud.companyCreate(company.getUuid(), company.getCompanyName());
    }

    public Department createDepartment() {
        var departmentName = emailAppConsole.inputDepartmentName();
        System.out.print("Type company name to add department to particular company: ");
        var companyName = emailAppConsole.readLine();

        for (Company company : emailAppCrud.getCompanyList()) {
            if (company.getCompanyName().equals(companyName)) {
                final var department = new Department(UUID.randomUUID(), departmentName);
                company.getDepartmentList().add(department);
                System.out.println("Added department to" + companyName);
                System.out.println();
                return department;
            }
        }
        throw new IllegalStateException();
    }

    public Employee createEmployee() {
        System.out.print("Type employee company name from list: \n");
        var companyName = emailAppConsole.readLine();
        for (Company company : emailAppCrud.getCompanyList()) {
            if (!company.getCompanyName().equals(companyName)) { //dlacvzego tutaj nie moge zrobic negacji ??
         }
            //else return; - jak wyjsc z metody do menu bez parametrow
        }
        System.out.print("Type employee firstname: ");
        var firstName = emailAppConsole.readLine();
        System.out.print("Type employee lastName: ");
        var lastName = emailAppConsole.readLine();
        System.out.println(emailAppCrud.getCompanyList());
        System.out.print("Type employee department name: ");
        for (Company company : emailAppCrud.getCompanyList()) {
            if (company.getCompanyName().equals(companyName)) {
                //companyName = company.getCompanyName();
                var departmentName = emailAppConsole.readLine();
                for (Department department : company.getDepartmentList()) {
                    if (department.getDepartmentName().equals(departmentName)) {
                        final var employee = new Employee(UUID.randomUUID(), firstName, lastName, newEmailAddress(firstName, lastName, companyName, emailAppConsole.generatePassword(5), 100));
                        department.getEmployeeList().add(employee);
                    }
                    departmentName = department.getDepartmentName();
                    System.out.println();
                }
            }
        }

        /*String address = firstName + "." + lastName + "." + departmentName + "@" + companyName + ".com";
        int capacity = 100;
        String password = generatePassword(5);
        Email email = new Email(password, capacity, address);
        var employee = new Employee(UUID.randomUUID(), firstName, lastName, email);
        //var employee = new Employee(firstName, lastName, departmentName, email);
        return employee;*/
        /*Employee employee = emailAppConsole.inputEmployeeData();
        return emailAppCrud.employeeCreate(employee.getUuid(), employee.getFirstName(), employee.getLastName(), employee.getEmail());*/
    }

    public Email newEmailAddress(String firstName, String lastName, String companyName, String password, int capacity) {
        var address = firstName + "." + lastName + "." + "@" + companyName + ".com";
        Email email = new Email(address, password, capacity);
        return email;
    }

    public void showCompanies() {
        emailAppCrud.getCompanyList().stream()
                .map(Company::getCompanyName)
                .forEach(System.out::println);
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }


}
