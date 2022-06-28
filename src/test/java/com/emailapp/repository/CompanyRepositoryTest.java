package com.emailapp.repository;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Email;
import com.emailapp.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class CompanyRepositoryTest {

    private final Employee employee1 = new Employee(UUID.randomUUID(), "Adam", "Kowalski", new Email("Adam.Kowalski.Sales@abc.com", "abc", 100));
    private final Employee employee2 = new Employee(UUID.randomUUID(), "Jan", "Nowak", new Email("Jan.Nowak.Sales@abc.com", "abc", 100));
    private final Employee employee3 = new Employee(UUID.randomUUID(), "Adrian", "Wiśniewski", new Email("Adrian.Wiśniewski.Sales@abc.com", "abc", 100));
    private final Employee employee4 = new Employee(UUID.randomUUID(), "Dariusz", "Nowacki", new Email("Dariusz.Nowacki.Sales@abc.com", "abc", 100));
    private final Employee employee5 = new Employee(UUID.randomUUID(), "Jacek", "Kamiński", new Email("Jacek.Kamiński.Sales@abc.com", "abc", 100));
    private final Employee employee6 = new Employee(UUID.randomUUID(), "Piotr", "Lewandowski", new Email("Piotr.Lewandowski.Sales@abc.com", "abc", 100));
    private final Employee employee7 = new Employee(UUID.randomUUID(), "Jarosław", "Kowalczyk", new Email("Jarosław.Kowalczyk.Sales@abc.com", "abc", 100));
    private final Employee employee8 = new Employee(UUID.randomUUID(), "Wiesław", "Zieliński", new Email("Wiesław.Zieliński.Sales@abc.com", "abc", 100));
    private final Employee employee9 = new Employee(UUID.randomUUID(), "Aleksander", "Szymański", new Email("Aleksander.Szymański.Sales@abc.com", "abc", 100));
    private final Employee employee10 = new Employee(UUID.randomUUID(), "Adam", "Kowalski", new Email("Adam.Kowalski.Service@abc.com", "abc", 100));
    private final Employee employee11 = new Employee(UUID.randomUUID(), "Jan", "Nowak", new Email("Jan.Nowak.Service@abc.com", "abc", 100));
    private final Employee employee12 = new Employee(UUID.randomUUID(), "Janek", "Nowakowski", new Email("Janek.Nowakowski.Service@abc.com", "abc", 100));
    private final Employee employee13 = new Employee(UUID.randomUUID(), "Adrian", "Wiśniewski", new Email("Adrian.Wiśniewski.Service@abc.com", "abc", 100));
    private final Employee employee14 = new Employee(UUID.randomUUID(), "Dariusz", "Nowacki", new Email("Dariusz.Nowacki.Service@abc.com", "abc", 100));
    private final Employee employee15 = new Employee(UUID.randomUUID(), "Jacek", "Kamiński", new Email("Jacek.Kamiński.Service@abc.com", "abc", 100));
    private final Employee employee16 = new Employee(UUID.randomUUID(), "Piotr", "Lewandowski", new Email("Piotr.Lewandowski.Service@abc.com", "abc", 100));
    private final Employee employee17 = new Employee(UUID.randomUUID(), "Jarosław", "Kowalczyk", new Email("Jarosław.Kowalczyk.Service@abc.com", "abc", 100));
    private final Employee employee18 = new Employee(UUID.randomUUID(), "Wiesław", "Zieliński", new Email("Wiesław.Zieliński.Service@abc.com", "abc", 100));
    private final Employee employee19 = new Employee(UUID.randomUUID(), "Aleksander", "Szymański", new Email("Aleksander.Szymański.Service@abc.com", "abc", 100));
    private final Employee employee20 = new Employee(UUID.randomUUID(), "Adam", "Kowalski", new Email("Adam.Kowalski.Marketing@google.com", "abc", 100));
    private final Employee employee21 = new Employee(UUID.randomUUID(), "Jan", "Nowak", new Email("Jan.Nowak.Marketing@google.com", "abc", 100));
    private final Employee employee22 = new Employee(UUID.randomUUID(), "Franek", "Trędowaty", new Email("Franek.Trędowaty.Marketing@google.com", "abc", 100));
    private final Employee employee23 = new Employee(UUID.randomUUID(), "Adrian", "Wiśniewski", new Email("Adrian.Wiśniewski.Marketing@google.com", "abc", 100));
    private final Employee employee24 = new Employee(UUID.randomUUID(), "Dariusz", "Nowacki", new Email("Dariusz.Nowacki.Marketing@google.com", "abc", 100));
    private final Employee employee25 = new Employee(UUID.randomUUID(), "Jacek", "Kamiński", new Email("Jacek.Kamiński.Marketing@google.com", "abc", 100));
    private final Employee employee26 = new Employee(UUID.randomUUID(), "Piotr", "Lewandowski", new Email("Piotr.Lewandowski.Marketing@google.com", "abc", 100));
    private final Employee employee27 = new Employee(UUID.randomUUID(), "Jarosław", "Kowalczyk", new Email("Jarosław.Kowalczyk.Marketing@google.com", "abc", 100));
    private final Employee employee28 = new Employee(UUID.randomUUID(), "Wiesław", "Zieliński", new Email("Wiesław.Zieliński.Marketing@google.com", "abc", 100));
    private final Employee employee29 = new Employee(UUID.randomUUID(), "Aleksander", "Szymański", new Email("Aleksander.Szymański.Marketing@google.com", "abc", 100));
    private final Employee employee30 = new Employee(UUID.randomUUID(), "Adam", "Kowalski", new Email("Adam.Kowalski.Finance@google.com", "abc", 100));
    private final Employee employee31 = new Employee(UUID.randomUUID(), "Jan", "Nowak", new Email("Jan.Nowak.Finance@google.com", "abc", 100));
    private final Employee employee32 = new Employee(UUID.randomUUID(), "Janek", "Nowakowski", new Email("Janek.Nowakowski.Finance@google.com", "abc", 100));
    private final Employee employee33 = new Employee(UUID.randomUUID(), "Adrian", "Wiśniewski", new Email("Adrian.Wiśniewski.Finance@google.com", "abc", 100));
    private final Employee employee34 = new Employee(UUID.randomUUID(), "Dariusz", "Nowacki", new Email("Dariusz.Nowacki.Finance@google.com", "abc", 100));
    private final Employee employee35 = new Employee(UUID.randomUUID(), "Jacek", "Kamiński", new Email("Jacek.Kamiński.Finance@google.com", "abc", 100));
    private final Employee employee36 = new Employee(UUID.randomUUID(), "Piotr", "Lewandowski", new Email("Piotr.Lewandowski.Finance@google.com", "abc", 100));
    private final Employee employee37 = new Employee(UUID.randomUUID(), "Jarosław", "Kowalczyk", new Email("Jarosław.Kowalczyk.Finance@abgooglec.com", "abc", 100));
    private final Employee employee38 = new Employee(UUID.randomUUID(), "Wiesław", "Zieliński", new Email("Wiesław.Zieliński.Finance@google.com", "abc", 100));
    private final Employee employee39 = new Employee(UUID.randomUUID(), "Aleksander", "Szymański", new Email("Aleksander.Szymański.Finance@google.com", "abc", 100));

    private final Department department1 = new Department(UUID.randomUUID(), "Sales", new ArrayList<>(List.of(employee1, employee2, employee3, employee4, employee5, employee6, employee7, employee8, employee9)));
    private final Department department2 = new Department(UUID.randomUUID(), "Service", new ArrayList<>(List.of(employee10, employee11, employee12, employee13, employee14, employee15, employee16, employee17, employee18, employee19)));
    private final Department department3 = new Department(UUID.randomUUID(), "Marketing", new ArrayList<>(List.of(employee20, employee21, employee22, employee23, employee24, employee25, employee26, employee27, employee28, employee29)));
    private final Department department4 = new Department(UUID.randomUUID(), "Finance", new ArrayList<>(List.of(employee30, employee31, employee32, employee33, employee34, employee35, employee36, employee37, employee38, employee39)));

    private Company company3 = new Company(UUID.randomUUID(), "Apple", new ArrayList<>(List.of(department1, department2)));
    private Company company4 = new Company(UUID.randomUUID(), "Dell", new ArrayList<>(List.of(department3, department4)));
    private List<Company> companyList2 = new ArrayList<>(List.of(company3, company4));

    private CompanyRepository companyRepository = new CompanyRepository(new ArrayList<>(companyList2));

    @Test
    void should_add_company() {
        // given
        final var uuid = randomUUID();
        final var name = "Nazwa firmy";

        // when
        final var result = companyRepository.companyCreate(uuid, name);

        // then
        assertEquals(uuid, result.getUuid());
        assertEquals(name, result.getCompanyName());
    }

    @Test
    void should_get_all_companies() {
        // given
        // when
        final var result = companyRepository.getCompanyList();

        // then
        assertFalse(result.isEmpty());
        assertEquals(companyList2, result);
    }

    @Test
    void should_update_company_name() {
        // given
        final var newCompanyName1 = "New company name1";
        final var newCompanyName2 = "New company name2";
        final var companyName1 = companyRepository.getCompanyList().get(0).getCompanyName();
        final var companyName2 = companyRepository.getCompanyList().get(1).getCompanyName();

        // when
        companyRepository.companyNameUpdate(companyName1, newCompanyName1);
        companyRepository.companyNameUpdate(companyName2, newCompanyName2);

        // then
        assertEquals(newCompanyName1, companyRepository.getCompanyList().stream()
                .map(Company::getCompanyName)
                .filter(c -> c.equals(newCompanyName1))
                .findAny().get());

        assertEquals(newCompanyName2, companyRepository.getCompanyList().stream()
                .map(Company::getCompanyName)
                .filter(c -> c.equals(newCompanyName2))
                .findAny().get());
    }

    @Test
    void should_delete_company() {
        // given
        var name = companyRepository.getCompanyList().get(0).getCompanyName();

        // when
        companyRepository.getCompanyList().remove(name);

        // then
        assertDoesNotThrow(() -> companyRepository.companyDelete(name));
        assertEquals(name, companyList2.stream()
                .map(Company::getCompanyName)
                .filter(c -> c.equals(name))
                .findAny().get());

    }
}