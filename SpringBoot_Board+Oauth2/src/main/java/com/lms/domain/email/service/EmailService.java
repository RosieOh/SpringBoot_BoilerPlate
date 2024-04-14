package com.lms.domain.email.service;

import com.lms.domain.email.dto.EmailMessage;
import com.lms.domain.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    private final MemberService memberService;

    // 회원 인증 이메일 보내기
    public String joinSendMail(EmailMessage emailMessage, String type) {
        String authNum = createCode();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false,"UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());                       // 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject());             // 메일 제목
            mimeMessageHelper.setText(setContext(authNum, type), true);     // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("Success");
            return authNum;
        } catch (MessagingException e) {
            log.info("인증 요청에 실패 했습니다.");
            throw new RuntimeException(e);
        }
    }


    // 랜덤 인증 코드 생성기 1
    private String createCode() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);
            switch (index) {
                case 0: stringBuffer.append((char) ((int) random.nextInt(26) + 97));
                    break;
                case 1: stringBuffer.append((char) ((int) random.nextInt(26) + 97));
                default: stringBuffer.append(random.nextInt(9));
            }
        }
        return stringBuffer.toString();
    }


    // JSP를 통한 html 적용
    public String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
}
