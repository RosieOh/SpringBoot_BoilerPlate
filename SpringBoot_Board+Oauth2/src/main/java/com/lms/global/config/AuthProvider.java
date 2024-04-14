package com.lms.global.config;

import com.lms.domain.member.entity.Member;
import com.lms.domain.member.service.MemberService;
import com.lms.global.cosntant.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@RequiredArgsConstructor
@Component
public class AuthProvider implements AuthenticationProvider {

    private final MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String pw = (String) authentication.getCredentials();

        PasswordEncoder passwordEncoder = memberService.passwordEncoder();
        UsernamePasswordAuthenticationToken token;
        Member memberToken = memberService.LoginEmail(email);

        if(memberToken != null && passwordEncoder.matches(pw, memberToken.getPw())) {
            List<GrantedAuthority> roles = new ArrayList<>();
            if (memberToken.getRole().equals(Role.ADMIN)) {
                roles.add(new SimpleGrantedAuthority("ADMIN")); // ADMIN 권한 부여

            } else if (memberToken.getRole().equals(Role.MANAGER)) {
                roles.add(new SimpleGrantedAuthority("MANAGER"));   // MANAGER 권한 부여

            } else {
                roles.add(new SimpleGrantedAuthority("STUDENT"));       // STUDENT 권한 부여
            }
            token = new UsernamePasswordAuthenticationToken(memberToken.getEmail(), null, roles);

            return token;
        } throw new BadCredentialsException("No such Member or Wrong Password.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
