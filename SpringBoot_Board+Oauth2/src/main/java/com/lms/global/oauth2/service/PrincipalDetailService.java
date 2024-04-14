package com.lms.global.oauth2.service;

import com.lms.domain.member.entity.Member;
import com.lms.domain.member.repository.MemberRepository;
import com.lms.global.oauth2.domain.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


// 시큐리티 설정에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetails Service 타입으로 IOC 되어 있는 loadUserByUsername 함수 실행됨
@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<Member> member = memberRepository.findByName(name);
        if (member.isPresent()) {
            System.out.println("member = " + member.get());
            return new PrincipalDetails(member.get());
        }
        return null;
    }
}
