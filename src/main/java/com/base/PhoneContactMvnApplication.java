package com.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.security", "com.database"})
public class PhoneContactMvnApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneContactMvnApplication.class, args);
    }

}
