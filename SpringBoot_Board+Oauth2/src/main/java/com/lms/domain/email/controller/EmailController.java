package com.lms.domain.email.controller;

import com.lms.domain.email.dto.EmailMessage;
import com.lms.domain.email.dto.EmailPost;
import com.lms.domain.email.dto.EmailResponse;
import com.lms.domain.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    // 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping(value = "/email")
    public ResponseEntity sendJoinMail(@RequestBody EmailPost emailPost) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPost.getEmail())
                .subject("[천재IT교육센터] 이메일 인증을 위한 인증 코드 발송")
                .build();

        String code = emailService.joinSendMail(emailMessage, "email/email");

        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setCode(code);

        return ResponseEntity.ok(emailResponse);
    }
}
