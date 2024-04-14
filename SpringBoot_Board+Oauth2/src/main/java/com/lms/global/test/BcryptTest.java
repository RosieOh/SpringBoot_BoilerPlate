package com.lms.global.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTest {
    public static void main(String[] args) {
        String pw = "458963!asd";
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        System.out.println(pwEncoder.encode(pw));
    }
}