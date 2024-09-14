package com.discord.config;

import com.discord.utils.CacheUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CacheUtils<String, String> expiringKeyUtils() {
        return new CacheUtils<>();
    }
}
