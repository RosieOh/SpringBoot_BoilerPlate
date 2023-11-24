package com.chunjae.test04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Test04Application {

    public static void main(String[] args) {
        SpringApplication.run(Test04Application.class, args);
    }
}
