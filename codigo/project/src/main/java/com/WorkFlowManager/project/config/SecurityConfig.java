package com.WorkFlowManager.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.WorkFlowManager.project.service.AppUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AppUserDetailsService appUserDetailsService;
    private final PasswordEncoder       passwordEncoder;

    public SecurityConfig(AppUserDetailsService appUserDetailsService, PasswordEncoder passwordEncoder) {
        this.appUserDetailsService = appUserDetailsService;
        this.passwordEncoder       = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Permite acesso público às páginas de informação e arquivos estáticos
                .requestMatchers("/previa", "/home", "/", "/historico", "/militares", "/restricoes", "/login", "/css/**", "/js/**", "/img/**").permitAll() 
                // Exige autenticação para qualquer outra página
                .anyRequest().authenticated()      
                )
                .formLogin(form -> form
                // Define a página de login personalizada
                .loginPage("/login")
                // Redireciona para /home após login bem-sucedido           
                .defaultSuccessUrl("/home", true) 
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder);
        return auth.build();
    }
}