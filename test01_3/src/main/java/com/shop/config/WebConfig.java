package com.shop.config;

import com.shop.service.BookService;
import com.shop.service.BookServiceImpl;
import com.shop.service.ItemService;
import com.shop.service.ItemServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebConfig {

    @Bean
    public ItemService itemService() { return new ItemServiceImpl(); }

}
