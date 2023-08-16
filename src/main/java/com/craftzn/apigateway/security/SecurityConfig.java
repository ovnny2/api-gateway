package com.craftzn.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()

                .x509(Customizer.withDefaults())

                .authorizeExchange((authorize) -> authorize
                        .pathMatchers("/login/**", "/error**").permitAll()
                        .anyExchange().authenticated())

                .oauth2Login(Customizer.withDefaults())

                .build();
    }
}