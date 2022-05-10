package com.emailapp;

import com.emailapp.service.InputProcessor;
import com.emailapp.repository.CompanyRepository;
import com.emailapp.service.CompanyService;
import com.emailapp.service.PasswordGenerator;

public class EmailApp {
    public static void main (String[] args) {

        var emailAppService = new CompanyService(new CompanyRepository(), new InputProcessor(), new PasswordGenerator());

        emailAppService.process();
    }
}
