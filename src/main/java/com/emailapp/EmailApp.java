package com.emailapp;

import com.emailapp.service.EmailAppService;

public class EmailApp {
    public static void main (String[] args) {

        EmailAppService emailAppService = new EmailAppService();
        emailAppService.process();
    }
}
