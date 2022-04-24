package com.emailapp;

import com.emailapp.model.Company;
import com.emailapp.model.Department;
import com.emailapp.model.Employee;
import com.emailapp.service.EmailAppConsole;
import com.emailapp.service.EmailAppCrud;
import com.emailapp.service.EmailAppService;

import java.util.ArrayList;

public class EmailApp {
    public static void main (String[] args) {


        final var emailAppService = new EmailAppService(new EmailAppCrud(), new EmailAppConsole());

        emailAppService.process();
    }
}
