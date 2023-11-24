package com.chunjae.test06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN"), EMP("EMP"), USER("USER");
    private String value;
}
