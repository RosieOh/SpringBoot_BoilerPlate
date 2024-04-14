package com.lms.domain.email.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class EmailMessage {
    private String to;
    private String subject;
    private String message;

}
