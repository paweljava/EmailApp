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
            System.out.println("Type \"5\" to show departmets in company ");
            System.out.println("Type \"6\" to show employees ");
            System.out.println("Type \"7\" to edit company name ");
            System.out.println("Type \"8\" to edit department name in company ");
            System.out.println("Type \"9\" to edit employee ");
            System.out.println("Type \"10\" to delete company ");
            System.out.println("Type \"11\" to delete department ");
            System.out.println("Type \"12\" to delete employee ");
            System.out.print("What is yours choose: ");
            String choose = scanner.nextLine();
            System.out.println();

            switch (choose) {
                case "exit" -> exit();
                case "1" -> createCompany();
                case "2" -> createDepartment();
                case "3" -> createEmployee();
                case "4" -> showCompanies();
                case "5" -> showDepartments();
                case "6" -> showEmployees();
                // case "7" -> ;
                // case "8" -> ;
                // case "9" -> ;
                case "10" -> companyDelete();
                case "11" -> departmentDelete();
                //case "12" -> employeeDelete();
                default -> System.out.println("Type correct value!");
            }
        }
    }


    /*
        public Company createCompany() {
            Company company = emailAppConsole.inputCompanyName();
            return emailAppCrud.companyCreate(company.getUuid(), company.getCompanyName());
        }
    */
    public Company createCompany() {
        var name = emailAppConsole.inputCompanyName();
        return emailAppCrud.companyCreate(UUID.randomUUID(), name);
    }


    public void createDepartment() {
        var departmentName = emailAppConsole.inputDepartmentName();
        System.out.print("Type company name to add department to particular company: ");
        var companyName = emailAppConsole.inputCompanyName();
        if (!isCompanyExist(companyName)) {
            return;
        }

        for (Company company : emailAppCrud.getCompanyList()) {
            if (company.getCompanyName().equals(companyName)) {
                var department = new Department(UUID.randomUUID(), departmentName);
                company.getDepartmentList().add(department);
                System.out.println("Added department to: " + companyName);
                System.out.println();
                return;
            }
        }
        throw new IllegalStateException();
    }

    public void createEmployee() {
        System.out.print("Type employee company name from list: \n");
        showCompanies();
        var companyName = emailAppConsole.inputCompanyName();
        if (!isCompanyExist(companyName)) {
            return;
        }
        System.out.print("Type employee firstname: ");
        var firstName = emailAppConsole.readLine();
        System.out.print("Type employee lastname: ");
        var lastName = emailAppConsole.readLine();
        System.out.println(emailAppCrud.getCompanyList());
        System.out.println("Type employee department name from list: ");
        showDepartments();
        var departmentName = emailAppConsole.readLine();
        isDepartmentExist(companyName, departmentName);
        // Czy ta petle ponizej robic streamami czy zostaje jak jest ?
        for (Company company : emailAppCrud.getCompanyList()) {
            if (company.getCompanyName().equals(companyName)) {
                for (Department department : company.getDepartmentList()) {
                    if (department.getDepartmentName().equals(departmentName)) {
                        final var employee = new Employee(UUID.randomUUID(), firstName, lastName, newEmailAddress(firstName, lastName, companyName, emailAppConsole.generatePassword(5), 100));
                        department.getEmployeeList().add(employee);
                        System.out.println();
                        System.out.println("Employee created: ");
                        System.out.println(companyName);
                        System.out.println(departmentName);
                        System.out.println(firstName);
                        System.out.println(lastName);
                        System.out.println();
                        return;
                    }
                }
            }
        }
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
        System.out.println();
    }

    public void showDepartments() {
        System.out.println("Type company name to display departments: ");
        var companyName = emailAppConsole.inputCompanyName();

        /* stream bez zagniezdzania
        var company = emailAppCrud.getCompanyList().stream()
                .filter((Company companies) -> companies.getCompanyName().equals(companyName))
                .findAny().get();
        company.getDepartmentList().stream()
                .map(Department::getDepartmentName)
                .forEach(System.out::println);*/

        // a tutaj stream z zagniezdzaniem streama
        emailAppCrud.getCompanyList().stream()
                .filter((Company c) -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .map(Department::getDepartmentName)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showEmployees() {
        System.out.println("Type company name: ");
        final var companyName = emailAppConsole.readLine();
        isCompanyExist(companyName);
        System.out.println("Type department name: ");
        final var departmentName = emailAppConsole.readLine();
        isDepartmentExist(companyName, departmentName);
        /*final var company = emailAppCrud.getCompanyList().stream()
                .filter((Company companies) -> companies.getCompanyName().equals(companyName))
                .findAny().get();
        final var department = company.getDepartmentList().stream()
                .filter((Department departments) -> departments.getDepartmentName().equals(departmentName))
                .findAny().get();
        department.getEmployeeList().stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName() + " " + employee.getEmail().getAddress().toLowerCase())
                .forEach(System.out::println);*/

        emailAppCrud.getCompanyList().stream()
                .filter((Company c) -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .filter((Department d) -> d.getDepartmentName().equals(departmentName))
                .flatMap(d -> d.getEmployeeList().stream())
                .forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getEmail()));
        System.out.println();

    }

    public void companyDelete() {
        var companyName = emailAppConsole.inputCompanyName();
        if (!isCompanyExist(companyName)) {
            return;
        }
        emailAppCrud.companyDelete(companyName);
    }

    public void departmentDelete() {
        System.out.println("Type company name ");
        var companyName = emailAppConsole.inputCompanyName();

    }

    public boolean isCompanyExist(String companyName) {
        if (!emailAppCrud.getCompanyList().stream().anyMatch(company -> company.getCompanyName().equals(companyName))) {
            System.out.println("Wrong company name or not exist");
            System.out.println();
            return false;
        } else return true;
    }

    public void isDepartmentExist(String companyName, String departmentName) {
        //System.out.println("Type department name of company: " + companyName);
        final var company = emailAppCrud.getCompanyList().stream()
                .filter((Company companies) -> companies.getCompanyName().equals(companyName))
                .findAny().get();
        /*final var department = company.getDepartmentList().stream()
                .filter((Department r) -> r.getDepartmentName().equals(departmentName))
                .findAny().get();*/
        if (company.getDepartmentList().stream()
                .noneMatch(departments -> company.getDepartmentList().equals(departmentName))) {
            System.out.println("Wrong department name or not exist");
            System.out.println();
            return;
        }
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }


}
