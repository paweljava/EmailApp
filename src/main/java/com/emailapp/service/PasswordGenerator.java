package com.emailapp.service;

import java.util.Random;

import static com.emailapp.service.InputProcessor.passwordSource;
//pozbyc sie statycznego importu
public class PasswordGenerator {

    public String generatePassword(int passwordSize) {
        Random random = new Random();
        var result = new StringBuilder();
        for (int i = 0; i < passwordSize; i++) {
            result.append(passwordSource.toCharArray()[random.nextInt(passwordSource.length())]);
        }
        return result.toString();
    }
}

