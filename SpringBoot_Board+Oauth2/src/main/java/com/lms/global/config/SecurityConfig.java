package com.lms.global.config;

import com.lms.global.oauth2.service.OAuth2MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2MemberService oAuth2MemberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("-------------------  filter Chain  ------------------");


        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorizeRequests) -> {authorizeRequests.requestMatchers(new AntPathRequestMatcher("**")).permitAll();})
                .authorizeHttpRequests((authorizeRequests) -> {authorizeRequests.requestMatchers(new AntPathRequestMatcher("/")).permitAll();})
            .formLogin((formLogin) -> formLogin
                        .loginPage("/member/login")
                        .failureUrl("/member/login?error=true")
                        .loginProcessingUrl("/loginPro")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("pw"))
            .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .exceptionHandling((exceptionHandling) -> {
                    exceptionHandling
                            .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                            .accessDeniedPage("/denied") // 접근 거부 페이지 설정
                            .defaultAuthenticationEntryPointFor(new Http403ForbiddenEntryPoint(), new AntPathRequestMatcher("/error-500")); // 500 에러 페이지 설정
                })


                .headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));
        http.oauth2Login(oauth2Login -> {
            oauth2Login
                    .loginPage("/") // OAuth2 로그인 페이지 설정
                    .defaultSuccessUrl("/") // OAuth2 로그인 성공 시 이동할 URL 설정
                    .userInfoEndpoint(infoEndpoint ->
                            infoEndpoint.userService(oAuth2MemberService));
            oauth2Login.clientRegistrationRepository(clientRegistrationRepository());
        });

        return http.build();
    }

    private ClientRegistrationRepository clientRegistrationRepository() {

        return new InMemoryClientRegistrationRepository(
                ClientRegistration.withRegistrationId("kakao")
                        .clientId("46f4d4e5de8617f1b2cc41630d23642a")
                        .clientSecret("4lTEsqCbbs1jfI1G3B9vKQfuJBdLCz87")
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .redirectUri("http://13.209.34.9:8080/login/oauth2/code/kakao")
                        .scope("profile_nickname", "account_email")
                        .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                        .tokenUri("https://kauth.kakao.com/oauth/token")
                        .userInfoUri("https://kapi.kakao.com/v2/user/me")
                        .userNameAttributeName("id")
                        .clientName("kakao-login")
                        .build()
        );
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("-------------------- WebSecurity ----------------------");
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}




