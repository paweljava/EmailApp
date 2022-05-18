package com.emailapp.service;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;
import com.emailapp.repository.CompanyRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class CompanyServiceTest {

    private InputProcessor inputProcessor = mock(InputProcessor.class);
    private CompanyRepository companyRepository = mock(CompanyRepository.class);
    private PasswordGenerator passwordGenerator = mock(PasswordGenerator.class);
    private CompanyService companyService = new CompanyService(companyRepository, inputProcessor, passwordGenerator);
    //private CompanyService companyService = new CompanyService(new CompanyRepository(), new InputProcessor(), new PasswordGenerator());

    @Test
    void createCompany() {
        // given
        final var id = randomUUID();
        var company1 = new Company("Samsung");
        var company2 = new Company("Sony");
        var company3 = new Company("Panasonic");
        var company4 = new Company("Sharp");
        var companyList = new ArrayList<>(List.of(company1, company2, company3));
        final String name = "Sharp";
        given(inputProcessor.inputCompanyName()).willReturn("Sony");
        //given(companyRepository.getCompanyList()).willReturn(new ArrayList<>(List.of(company1, company2, company3)));
        given(companyRepository.getCompanyList()).willReturn(companyList);
        //given(companyRepository.companyCreate(id, name)).willReturn();

        // when
        companyService.createCompany();
        //var result = companyServiceTest.createCompany();
        System.out.println("przed petla");
        companyRepository.getCompanyList().stream()
                .map(Company::getCompanyName)
                .forEach(System.out::println);
        System.out.println("za petla");

        // then
        assertEquals(name, companyRepository.getCompanyList().stream()
                .map(c -> c.getCompanyName())
                .filter(c -> c.equals(name))
                .findAny().get());
    }

    @Test
    void createDepartment() {
        // given
        final var id = randomUUID();
        final var departmentName = "Production";
        final var companyName = "Apple";
        final var company = new Company(id, companyName);
        final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputDepartmentName()).willReturn(departmentName);
        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);

        // when
        companyService.createDepartment();

        // then
        assertEquals(departmentName, company.getDepartmentList().stream()
                .map(Department::getDepartmentName)
                .filter(c -> c.equals(departmentName))
                .findAny().get());
    }

    @Test
    void createEmployee() {
        // given
        final var id = randomUUID();
        final var departmentName = "Production";
        final var companyName = "Apple";
        final var email = new Email("Adam.Kowalski.Produkcja@Szalas.com", "haslo", 100);
        final var employee = new Employee(id, "Adam", "Kowalski", email);
        final var employeeList = new ArrayList<>(List.of(employee));
        final var department = new Department(id, departmentName, employeeList);
        final var departmentList = new ArrayList<>(List.of(department));
        final var company = new Company(id, companyName, departmentList);
        final var companyList = new ArrayList<>(List.of(company));

/*
        var department = new Department(id, departmentName);
        var departmentList = new ArrayList<>(List.of(department));
        //var company = new Company(id, companyName, departmentList);
        final var company = new Company(id, companyName);*/
        //final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputEmployeeData()).willReturn(employee.getLastName());
        given(inputProcessor.inputDepartmentName()).willReturn(departmentName);
        given(inputProcessor.inputCompanyName()).willReturn(companyName);

        given(companyRepository.getCompanyList()).willReturn(companyList);
        given(companyRepository.getCompanyList().get(0).getDepartmentList()).willReturn(List.of(department));

        // when
        companyService.createEmployee();

        // then
        assertEquals(employee.getLastName(), department.getEmployeeList().stream()
                .map(Employee::getLastName)
                .filter(c -> c.equals(employee.getLastName()))
                .findAny().get());
    }

    @Test
    void newEmailAddress() {
        // given

        // when

        // then
    }

    @Test
    void showCompanies() {
        // given

        // when

        // then
    }

    @Test
    void showDepartments() {
        // given

        // when

        // then
    }

    @Test
    void showEmployees() {
        // given

        // when

        // then
    }

    @Test
    void updateCompanyName() {
        // given

        // when

        // then
    }

    @Test
    void updateDepartmentName() {
        // given

        // when

        // then
    }

    @Test
    void updateEmployeeName() {
        // given

        // when

        // then
    }

    @Test
    void companyDelete() {
        // given

        // when

        // then
    }

    @Test
    void departmentDelete() {
        // given

        // when

        // then
    }

    @Test
    void employeeDelete() {
        // given

        // when

        // then
    }
}