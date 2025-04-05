package com.shopping.flipkart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
         .authorizeHttpRequests(auth -> auth
            .requestMatchers(
               new AntPathRequestMatcher("/swagger-ui/**"),
               new AntPathRequestMatcher("/v3/api-docs/**"),
               new AntPathRequestMatcher("/swagger-ui.html")
            ).permitAll()
            .anyRequest().permitAll() // You can change this to authenticated() if needed
         )
         .csrf(csrf -> csrf.disable()) // disables CSRF in modern syntax
         .httpBasic(Customizer.withDefaults())
         .build();
   }
}
