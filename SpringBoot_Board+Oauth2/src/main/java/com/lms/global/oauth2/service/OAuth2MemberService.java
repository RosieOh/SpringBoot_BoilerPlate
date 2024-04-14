package com.lms.global.oauth2.service;

import com.lms.domain.member.entity.Member;
import com.lms.domain.member.repository.MemberRepository;
import com.lms.global.oauth2.domain.PrincipalDetails;
import com.lms.global.oauth2.info.KakaoMemberInfo;
import com.lms.global.oauth2.info.OAuth2MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class OAuth2MemberService extends DefaultOAuth2UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2MemberInfo memberInfo = null;
        System.out.println("====================================================" + oAuth2User.getAttributes());
        System.out.println(userRequest.getClientRegistration().getRegistrationId());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("====================================================registrationId = " + registrationId);
        if(registrationId.equals("kakao")) {
            memberInfo = new KakaoMemberInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("로그인 실패");
        }

        String provider = memberInfo.getProvider();
        String providerId = memberInfo.getProviderId();
        String oauth2Id = provider = "_" + providerId;
        String name = memberInfo.getName();
        String email = memberInfo.getEmail();

        // 개인정보 심사 끝나면 사용할 예정
//        String tel = memberInfo.getTel();
        String role = "STUDENT";
        System.out.println("=======================================================Attributes Check = " + oAuth2User.getAttributes());
        Optional<Member> findMember = memberRepository.findByOauth2Id(oauth2Id);

        Member member = null;
        if(findMember.isEmpty()) {
            member = Member.builder()
                    .oauth2Id(oauth2Id)
                    .name(name)
                    .email(email)
//                    .tel(tel)
                    .pw(passwordEncoder.encode("pw"))
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberRepository.save(member);
        } else {
            member = findMember.get();
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());

    }
}
