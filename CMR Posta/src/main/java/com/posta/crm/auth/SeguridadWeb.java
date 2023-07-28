package com.posta.crm.auth;

import com.posta.crm.auth.filter.JwtAuthenticationFilter;
import com.posta.crm.auth.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadWeb {


    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authRules -> authRules
                        .requestMatchers(HttpMethod.PUT,"/users").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/plan/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/clients/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/diagEmp/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/financial/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/image/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/canvas/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/process/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/processEmpre/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/search/**").hasAnyRole("ADMIN","ADVISER")
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }
}
