package com.hopper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception{
       http.csrf().disable().cors().disable();
       http.authorizeHttpRequests().requestMatchers("/api/v1/users/**").permitAll()
               .anyRequest().authenticated();
       return http.build();
    }
}
