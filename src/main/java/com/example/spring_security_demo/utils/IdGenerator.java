package com.example.spring_security_demo.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class IdGenerator {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomId() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomDigit = random.nextInt(10);
            sb.append(randomDigit);
        }
        return sb.toString();
    }
}
