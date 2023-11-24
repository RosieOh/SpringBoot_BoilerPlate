package com.shop.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String tel;
    private String email;
    private String regDate;
}
