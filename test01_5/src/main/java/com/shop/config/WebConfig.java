package com.shop.config;

import com.shop.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebConfig {

    @Bean
    public ItemService itemService() { return new ItemServiceImpl(); }

    @Bean
    public UserService userService() { return new UserServiceImpl(); }
}
