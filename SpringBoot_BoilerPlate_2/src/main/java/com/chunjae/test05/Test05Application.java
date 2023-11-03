package com.chunjae.test05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Test05Application {

    public static void main(String[] args) {
        SpringApplication.run(Test05Application.class, args);
    }
}
