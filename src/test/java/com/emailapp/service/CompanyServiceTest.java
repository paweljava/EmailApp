package com.emailapp.service;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;
import com.emailapp.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class CompanyServiceTest {

    private InputProcessor inputProcessor = mock(InputProcessor.class);
    private CompanyRepository companyRepository = mock(CompanyRepository.class);
    private PasswordGenerator passwordGenerator = mock(PasswordGenerator.class);
    private CompanyService companyService = new CompanyService(companyRepository, inputProcessor, passwordGenerator);
    //private CompanyService companyService = new CompanyService(new CompanyRepository(), new InputProcessor(), new PasswordGenerator());


    @Test
    void createCompany() {
        // given
        // when
        companyService.createCompany();

        // then
        verify(companyRepository).companyCreate(any(), any());
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
        final var employeeList = new ArrayList<Employee>(List.of());
        final var department = new Department(id, departmentName, employeeList);
        final var departmentList = new ArrayList<>(List.of(department));
        final var company = new Company(id, companyName, departmentList);
        final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputEmployeeData()).willReturn(employee.getLastName());
        given(inputProcessor.inputDepartmentName()).willReturn(departmentName);
        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);

        // when
        companyService.createEmployee();

        // then
        verify(companyRepository, times(2)).getCompanyList();
    }

    @Test
    void newEmailAddress() {
        // given
        var firstName = "Jan";
        var lastName = "Kowalski";
        var companyName = "Microsoft";
        var password = "qwerty";
        var capacity = 199;
        var address = firstName + "." + lastName + "." + "@" + companyName + ".com";

        // when
        var result = companyService.newEmailAddress(firstName, lastName, companyName, password, capacity);

        // then
        assertEquals(address, result.getAddress());
        assertEquals(password, result.getPassword());
        assertEquals(capacity, result.getCapacity());
    }

    @Test
    void showCompanies() {
        // given

        // when
        companyRepository.getCompanyList();
        // then
        verify(companyRepository).getCompanyList();
    }

    @Test
    void should_showDepartments() {
        // given
        final var id = randomUUID();
        final var departmentName = "Production";
        final var companyName = "Apple";
        final var department = new Department(id, departmentName);
        final var departmentList = new ArrayList<>(List.of(department));
        final var company = new Company(id, companyName, departmentList);
        final var companyList = new ArrayList<>(List.of(company));
        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);

        // when
        // then
        assertDoesNotThrow(() -> companyService.showDepartments());
        verify(companyRepository, times(2)).getCompanyList();

    }

    @Test
    void showEmployees() {
        // given

        given(inputProcessor.inputCompanyName()).willReturn("Apple");
        given(inputProcessor.inputDepartmentName()).willReturn("Sales");
        // when
        companyService.showEmployees();

        // then
        verify(companyService).showEmployees();
    }

    @Test
    void updateCompanyName() {
        // given
        final var id = randomUUID();
        final var companyName = "Apple";

        final var company = new Company(id, companyName);
        final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);

        // when
        companyService.updateCompanyName();

        // then
        verify(companyRepository).companyNameUpdate(any(), any());
    }

    @Test
    void should_updateDepartmentName() {
        // given
        final var id = randomUUID();
        final var departmentName = "Production";
        final var newDepartmentName = "Sales";
        final var companyName = "Apple";
        final var department = new Department(id, departmentName);
        final var departmentList = new ArrayList<>(List.of(department));
        final var company = new Company(id, companyName, departmentList);
        final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);
        given(inputProcessor.inputDepartmentName()).willReturn(departmentName);
        given(inputProcessor.inputNewDepartmentName()).willReturn(newDepartmentName);

        // when
        companyService.updateDepartmentName();

        // then
        assertDoesNotThrow(() -> companyService.showDepartments());
        verify(companyRepository, times(6)).getCompanyList();
    }

    @Test
    void should_updateEmployeeName() {
        // given
        final var id = randomUUID();
        final var departmentName = "Production";
        final var companyName = "Apple";
        final var email = new Email("Adam.Kowalski.Produkcja@Szalas.com", "haslo", 100);
        final var employee = new Employee(id, "Adam", "Kowalski", email);
        final var employeeList = new ArrayList<Employee>(List.of());
        final var department = new Department(id, departmentName, employeeList);
        final var departmentList = new ArrayList<>(List.of(department));
        final var company = new Company(id, companyName, departmentList);
        final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputEmployeeData()).willReturn(employee.getLastName());
        given(inputProcessor.inputDepartmentName()).willReturn(departmentName);
        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);
        // when
        companyService.updateEmployeeName();

        // then
        verify(companyService).updateEmployeeName();
    }

    @Test
    void companyDelete() {
        // given
        final var id = randomUUID();
        final var companyName = "Apple";
        final var company = new Company(id, companyName);
        final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);

        // when
        companyService.companyDelete();

        // then
        verify(companyRepository, atLeastOnce()).companyDelete(companyName);
    }

    @Test
    void departmentDelete() {
        // given
        final var id = randomUUID();
        final var departmentName = "Production";
        final var companyName = "Apple";
        final var department = new Department(id, departmentName);
        final var departmentList = new ArrayList<>(List.of(department));
        final var company = new Company(id, companyName, departmentList);
        final var companyList = new ArrayList<>(List.of(company));

        given(inputProcessor.inputDepartmentName()).willReturn(departmentName);
        given(inputProcessor.inputCompanyName()).willReturn(companyName);
        given(companyRepository.getCompanyList()).willReturn(companyList);
        // when
        companyService.departmentDelete();

        // then
        verify(companyService).departmentDelete();
    }

    @Test
    void employeeDelete() {
        // given
        // when
        companyService.employeeDelete();

        // then
        verify(companyService).employeeDelete();
    }
}