package com.shop.util;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    @NotBlank(message="로그인할 아이디를 입력하시기 바랍니다.")
    private String username;
    @NotBlank(message="로그인할 비밀번호를 입력하시기 바랍니다.")
    private String password;
}
