package com.emailapp.service;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;
import com.emailapp.repository.CompanyRepository;

import java.util.UUID;

public class CompanyService {

    /*    public EmailAppCrud emailAppCRUD = new EmailAppCrud();
        public EmailAppConsole emailAppConsole = new EmailAppConsole() {
        };*/
    private final CompanyRepository companyRepository;
    private final InputProcessor inputProcessor;
    private final PasswordGenerator passwordGenerator;

    public CompanyService(CompanyRepository companyRepository, InputProcessor inputProcessor, PasswordGenerator passwordGenerator) {
        this.companyRepository = companyRepository;
        this.inputProcessor = inputProcessor;
        this.passwordGenerator = passwordGenerator;
    }

    public void process() {
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
            String choose = inputProcessor.readLine();
            System.out.println();

            switch (choose) {
                case "exit" -> exit();
                case "1" -> createCompany();
                case "2" -> createDepartment();
                case "3" -> createEmployee();
                case "4" -> showCompanies();
                case "5" -> showDepartments();
                case "6" -> showEmployees();
                case "7" -> updateCompanyName();
                case "8" -> updateDepartmentName();
                case "9" -> updateEmployeeName();
                case "10" -> companyDelete();
                case "11" -> departmentDelete();
                case "12" -> employeeDelete();
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
    public void createCompany() {
        var name = inputProcessor.inputCompanyName();
        companyRepository.companyCreate(UUID.randomUUID(), name);
    }

    public void createDepartment() {
        System.out.print("Type company name to add department: ");
        var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        System.out.print("Type new department name: ");
        var departmentName = inputProcessor.inputDepartmentName();
        for (Company company : companyRepository.getCompanyList()) {
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
        var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        System.out.print("Type employee firstname: ");
        var firstName = inputProcessor.readLine();
        System.out.print("Type employee lastname: ");
        var lastName = inputProcessor.readLine();
        System.out.println(companyRepository.getCompanyList());
        System.out.println("Type employee department name from list: ");
        showDepartments();
        var departmentName = inputProcessor.readLine();
        isDepartmentExist(companyName, departmentName);
        // Czy ta petle ponizej robic streamami czy zostaje jak jest ?
        for (Company company : companyRepository.getCompanyList()) {
            if (company.getCompanyName().equals(companyName)) {
                for (Department department : company.getDepartmentList()) {
                    if (department.getDepartmentName().equals(departmentName)) {
                        final var employee = new Employee(UUID.randomUUID(),
                                firstName,
                                lastName,
                                newEmailAddress(firstName,
                                        lastName,
                                        companyName,
                                        passwordGenerator.generatePassword(5),100));
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
        companyRepository.getCompanyList().stream()
                .map(Company::getCompanyName)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showDepartments() {
        System.out.println("Type company name to display departments: ");
        var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }

        /* stream bez zagniezdzania
        var company = emailAppCrud.getCompanyList().stream()
                .filter((Company companies) -> companies.getCompanyName().equals(companyName))
                .findAny().get();
        company.getDepartmentList().stream()
                .map(Department::getDepartmentName)
                .forEach(System.out::println);*/

        // a tutaj stream z zagniezdzaniem streama
        companyRepository.getCompanyList().stream()
                .filter((Company c) -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .map(Department::getDepartmentName)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showEmployees() {
        System.out.println("Type company name: ");
        final var companyName = inputProcessor.readLine();
        if (isCompanyExist(companyName)) {
            return;
        }
        System.out.println("Type department name: ");
        final var departmentName = inputProcessor.inputDepartmentName();
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

        companyRepository.getCompanyList().stream()
                .filter((Company c) -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .filter((Department d) -> d.getDepartmentName().equals(departmentName))
                .flatMap(d -> d.getEmployeeList().stream())
                .forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getEmail()));
        System.out.println();

    }

    public void updateCompanyName() {
        System.out.print("Type company name to change: ");
        var companyName = inputProcessor.inputCompanyName();
        System.out.println(); // zobaczymy roznice
        System.out.print("Type new company name: ");
        var newCompanyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        companyRepository.companyNameUpdate(companyName, newCompanyName);
        System.out.println("Company name updated");
    }

    public void updateDepartmentName() {
        System.out.print("Type company name: ");
        var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        System.out.print("Type department name: ");
        var departmentName = inputProcessor.inputDepartmentName();
        if (!isDepartmentExist(companyName, departmentName)) {
            return;
        }
        System.out.print("Type new department name: ");
        var newDepartmentName = inputProcessor.inputCompanyName();
        var company = companyRepository.getCompanyList().stream()
                .filter(c -> c.getCompanyName().equals(companyName))
                .findAny().get();
        var departmentToUpdate = companyRepository.getCompanyList().stream()
                .filter(c -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .filter(d -> d.getDepartmentName().equals(departmentName))
                .findAny().get();
        company.getDepartmentList().add(new Department(departmentToUpdate.getUuid(), newDepartmentName));
        company.getDepartmentList().remove(departmentToUpdate);
        System.out.println("Department name updated \n");
    }

    public void updateEmployeeName() {
        System.out.print("Type company name: ");
        final var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        System.out.println();
        System.out.print("Type department name: ");
        final var departmentName = inputProcessor.inputDepartmentName();
        if (!isDepartmentExist(companyName, departmentName)) {
            return;
        }
        System.out.print("Type employee last name: ");
        final var employeeLastName = inputProcessor.readLine();
        System.out.println("Type employee new last name: ");
        final var newEmployeeLastName = inputProcessor.readLine();
        var department = companyRepository.getCompanyList().stream()
                .filter(c -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .filter(d -> d.getDepartmentName().equals(departmentName))
                .findAny().get();
        var employeeToUpdate = companyRepository.getCompanyList().stream()
                .filter(c -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .filter(d -> d.getDepartmentName().equals(departmentName))
                .flatMap(e -> e.getEmployeeList().stream())
                .filter(e -> e.getLastName().equals(employeeLastName))
                .findAny().get();

        department.getEmployeeList().add(new Employee(employeeToUpdate.getUuid(),
                employeeToUpdate.getFirstName(),
                newEmployeeLastName,
                newEmailAddress(employeeToUpdate.getFirstName(),
                        newEmployeeLastName,
                        companyName, employeeToUpdate.getEmail().getPassword(), 100)));
        department.getEmployeeList().remove(employeeToUpdate);
        System.out.println("Employee name updated");
        // druga wersja usuwania pracownika
        /*companyRepository.getCompanyList().stream()
                .filter(c -> c.getCompanyName().equals(companyName))
                .flatMap(c -> c.getDepartmentList().stream())
                .filter(d -> d.getDepartmentName().equals(departmentName))
                .forEach(d -> {
                            d.getEmployeeList().stream()
                                    .filter(e -> e.getLastName().equals(employeeLastName))
                                    .findFirst().ifPresent(e -> d.getEmployeeList().remove(e));
                        }
                );*/
    }

    public void companyDelete() {
        var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        companyRepository.companyDelete(companyName);
        System.out.println("Company deleted");
    }

    public void departmentDelete() {
        System.out.println("Type company name from list");
        showCompanies();
        var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        System.out.println("Type department name from list");
        showDepartments();
        var departmentName = inputProcessor.inputCompanyName();
        if (!isDepartmentExist(companyName, departmentName)) {
            return;
        }


        /*emailAppCrud.getCompanyList().stream()
                .filter(company -> company.getCompanyName().equals(companyName))
                .flatMap(d -> d.getDepartmentList().stream())
                .filter(department -> department.getDepartmentName().equals(departmentName)).
               // .map(d -> d.getDepartmentList().stream())
                .flatMap(d -> d.getDepartmentName().equals(departmentName))
                .f
        .fi                .filter(d -> d.().equals(departmentName))
                .findAny().get();
                .filter(department -> department.getDepartmentName().equals(departmentName))
        var departmentListToDelete = emailAppCrud.getCompanyList().stream()
                .filter(company -> company.getCompanyName().equals(companyName))
                .findAny().get();*/


        // wersja bez zagniezdzania
        var departmentToDelete = (companyRepository.getCompanyList().stream()
                .filter(company -> company.getCompanyName().equals(companyName))
                .flatMap(d -> d.getDepartmentList().stream())
                .filter(department -> department.getDepartmentName().equals(departmentName))
                .findAny().get());
        var findDepartmentList
                = companyRepository.getCompanyList().stream()
                .filter(company -> company.getCompanyName().equals(companyName))
                .findAny().get();
        findDepartmentList.getDepartmentList().remove(departmentToDelete);
    }

    public void employeeDelete() {
        System.out.println("Type company name from list");
        showCompanies();
        var companyName = inputProcessor.inputCompanyName();
        if (isCompanyExist(companyName)) {
            return;
        }
        System.out.println("Type department name from list");
        showDepartments();
        var departmentName = inputProcessor.inputCompanyName();
        if (!isDepartmentExist(companyName, departmentName)) {
            return;
        }
        System.out.println("Type employee name from list to delete");
        showEmployees();
        var employeeLastName = inputProcessor.readLine();
        if (!isEmployeeExist(companyName, departmentName, employeeLastName)) {
            return;
        }
        // wersja bez zagniezdzania
        var findEmployeeToDelete = (companyRepository.getCompanyList().stream()
                .filter(company -> company.getCompanyName().equals(companyName))
                .flatMap(d -> d.getDepartmentList().stream())
                .filter(department -> department.getDepartmentName().equals(departmentName))
                .flatMap(e -> e.getEmployeeList().stream())
                .filter(e -> e.getLastName().equals(employeeLastName))
                .findAny().get());
        var findEmployeeList = companyRepository.getCompanyList().stream()
                .filter(company -> company.getCompanyName().equals(companyName))
                .flatMap(d -> d.getDepartmentList().stream())
                .findAny().get();
        findEmployeeList.getEmployeeList().remove(findEmployeeToDelete);
    }

    public boolean isCompanyExist(String companyName) {
        if (companyRepository.getCompanyList().stream()
                .noneMatch(company -> company.getCompanyName().equals(companyName))) {
            System.out.println("Wrong company name or not exist");
            System.out.println();
            return true;
        } else return false;
    }

    public boolean isDepartmentExist(String companyName, String departmentName) {
        if (companyRepository.getCompanyList().stream()
                .filter(c -> c.getCompanyName().equals(companyName))
                .flatMap(d -> d.getDepartmentList().stream())
                .noneMatch(d -> d.getDepartmentName().equals(departmentName))) {
            System.out.println("Wrong department name or not exist");
            System.out.println();
            return false;
        } else return true;
    }

    public boolean isEmployeeExist(String companyName, String departmentName, String employeeLastName) {
        if (companyRepository.getCompanyList().stream()
                .filter(c -> c.getCompanyName().equals(companyName))
                .flatMap(d -> d.getDepartmentList().stream())
                .filter(d -> d.getDepartmentName().equals(departmentName))
                .flatMap(e -> e.getEmployeeList().stream())
                .noneMatch(e -> e.getLastName().equals(employeeLastName))) {
            System.out.println("Wrong department name or not exist");
            System.out.println();
            return false;
        } else return true;
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }


}
