package com.springbootstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStartApplication.class, args);
    }

}
