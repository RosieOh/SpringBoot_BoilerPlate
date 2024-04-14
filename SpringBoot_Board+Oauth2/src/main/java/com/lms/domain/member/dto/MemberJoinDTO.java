package com.lms.domain.member.dto;

import com.lms.global.cosntant.Role;
import com.lms.global.cosntant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberJoinDTO {

    private Long id;

    @NotBlank(message = "**")
    private String pw;

    @NotBlank(message = "**")
    private String name;

    @NotBlank(message = "**")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "**")
    private String code;

    @NotBlank(message = "**")
    private String tel;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = true)
    private LocalDateTime loginAt;  //최종 로그인시간

}