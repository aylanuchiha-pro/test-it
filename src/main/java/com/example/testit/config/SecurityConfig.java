package com.example.testit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz)-> {
                    authz
                            .requestMatchers(HttpMethod.DELETE, "/tasks/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/tasks/*").hasAnyRole("MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.POST, "/tasks").hasAnyRole("MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.POST, "/tasks/*/start").hasAnyRole("USER","MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.POST, "/tasks/*/finish").hasAnyRole("USER","MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.GET, "/tasks/**").hasAnyRole("USER","MANAGER","ADMIN")

                            .anyRequest().authenticated();
                })//On demande que toute les sessions soit authentifiée

                .httpBasic(Customizer.withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//On rend les session stateless

        return http.build();
    }
}
