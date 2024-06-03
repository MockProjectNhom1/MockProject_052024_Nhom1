package com.realeaste.MockProject_052024.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    UserDetailsService userDetailsService() {
        return new DemoUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    SecurityFilterChain configureHttpSecurity(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").hasAnyAuthority("Admin","Agent")
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .permitAll()
                ).logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    WebSecurityCustomizer configureWebSecurity() throws Exception{

        return (web) -> web.ignoring()
                .requestMatchers(
                        "/images/**","/webjars/**","/js/**","/css/**","/fontawesome/**","/webfonts/**");

    }
}