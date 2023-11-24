package com.shop.config;

import com.shop.service.BookService;
import com.shop.service.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BookService bookService() {        return new BookServiceImpl();    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/**", "/ex").permitAll()
                .antMatchers("/member/login", "/member/auth","/member/join", "/member/joinPro").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/teacher/**").hasAnyRole("ADMIN","TEACHER")
                .antMatchers("/member/update","/member/out","/member/updatePro").hasAnyRole("USER","ADMIN","TEACHER")
                .anyRequest().authenticated();
        http.csrf().disable().cors().disable();
        http.formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/member/auth")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/");
        http.logout()
                .logoutUrl("/member/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");
        return http.build();
    }
}
