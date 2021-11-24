package com.emailapp.service;

import com.emailapp.model.Department;
import org.junit.jupiter.api.Test;

public class EmailAppServiceTest {


    private final EmailAppService underTest = new EmailAppService();

    @Test
    void getEmployee() {
        //given
        var firstName = "Adam";
        var lastName = "Mickiewicz";
        Department department = Department.HR;
        //When
        var result = underTest.getEmployee();

        //Then
    }
}
